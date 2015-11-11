/**
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.seo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import com.arcbees.seo.widget.Image;
import com.arcbees.seo.widget.OgType;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadElement;
import com.google.gwt.dom.client.MetaElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwtmockito.GwtMockitoTestRunner;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.arcbees.seo.widget.Image.MimeType.JPEG;

@RunWith(GwtMockitoTestRunner.class)
public class TagsInjectorTest {
    private static final String SOME_TITLE = "SOME TITLE";
    private static final String SOME_DESCRIPTION = "SOME_DESCRIPTION";
    private static final String SOME_KEYWORDS = "SOME_KEYWORDS";
    private static final String FB_APP_ID = "12345";
    private static final String AN_URL = "AN_URL";
    private static final Image.MimeType MIME_TYPE = JPEG;
    private static final int HEIGHT = 480;
    private static final int WIDTH = 480;
    private static final int DEFAULT_INJECTIONS = 1;
    private static final String A_PROPERTY = "A_PROPERTY";
    private static final String A_VALUE = "A_VALUE";
    private static final String ANOTHER_VALUE = "ANOTHER_VALUE";

    private TagsInjector tagsInjector;
    private Document document;
    private HeadElement headElement;

    @Before
    public void setUp() {
        headElement = mock(HeadElement.class, RETURNS_DEEP_STUBS);
        document = mock(Document.class, RETURNS_DEEP_STUBS);
        when(document.getHead()).thenReturn(headElement);

        tagsInjector = new TagsInjector(document);
    }

    @Test
    public void injectTitle_withTitle_isInjectedForOgToo() {
        // given
        SeoElements seoElements = getBuilder()
                .withTitle(SOME_TITLE)
                .build();

        // when
        tagsInjector.inject(seoElements);

        // then
        verify(document).setTitle(SOME_TITLE);
        verify(document.getHead(), times(3)).insertFirst(any(Node.class));
    }

    @Test
    public void inject_withoutValues_injectsDefault() {
        // given
        SeoElements seoElements = getBuilder().build();

        // when
        tagsInjector.inject(seoElements);

        // then
        verify(document, never()).setTitle(anyString());
        verify(document.getHead(), times(DEFAULT_INJECTIONS)).insertFirst(any(Node.class));
    }

    @Test
    public void setMetaTag_withoutValue_isNotInjected() {
        // when
        tagsInjector.setMetaTag("some:property", null);

        // then
        verify(headElement, never()).insertFirst(any(Node.class));
    }

    @Test
    public void setMetaTag_withValue_isInjected() {
        // when
        tagsInjector.setMetaTag("some:property", "some_value");

        // then
        verify(headElement).insertFirst(any(Node.class));
    }

    @Test
    public void injectDescription_withValue_isInjectedForOgToo() {
        // given
        SeoElements seoElements = getBuilder().withDescription(SOME_DESCRIPTION).build();

        // when
        tagsInjector.inject(seoElements);

        // then
        verify(headElement, times(DEFAULT_INJECTIONS + 3)).insertFirst(any(Node.class));
    }

    @Test
    public void injectKeywords_withValue_isInjected() {
        // given
        SeoElements seoElements = getBuilder().withKeywords(SOME_KEYWORDS).build();

        // when
        tagsInjector.inject(seoElements);

        // then
        verify(headElement, times(DEFAULT_INJECTIONS + 1)).insertFirst(any(Node.class));
    }

    @Test
    public void injectFbAppId_withValue_isInjected() {
        // given
        SeoElements seoElements = getBuilder().withFbAppId(FB_APP_ID).build();

        // when
        tagsInjector.inject(seoElements);

        // then
        verify(headElement, times(DEFAULT_INJECTIONS + 1)).insertFirst(any(Node.class));
    }

    @Test
    public void injectOpenGraph_withType_isInjected() {
        // given
        OpenGraph openGraph = new OpenGraph.Builder().withType(OgType.TypeValue.ARTICLE).build();
        SeoElements seoElements = getBuilder().withOpenGraph(openGraph).build();

        // when
        tagsInjector.inject(seoElements);

        // then
        verify(headElement).insertFirst(any(Node.class));
    }

    @Test
    public void inject_withImage_isInjected() {
        // given
        com.arcbees.seo.Image image = new com.arcbees.seo.Image(AN_URL, HEIGHT, WIDTH, MIME_TYPE);
        SeoElements seoElements = getBuilder()
                .withImage(image)
                .build();

        // when
        tagsInjector.inject(seoElements);

        // then
        verify(headElement, times(DEFAULT_INJECTIONS + 5)).insertFirst(any(Node.class));
    }

    @Test
    public void injectTag_alreadyExists_isReplaced() {
        // given
        MetaElement metaElement = getMetaElementMock(A_PROPERTY, A_VALUE);
        NodeList<Element> nodeList = mock(NodeList.class);
        when(nodeList.getLength()).thenReturn(1);
        when(nodeList.getItem(0)).thenReturn(metaElement);
        when(headElement.getElementsByTagName("meta")).thenReturn(nodeList);

        // when
        tagsInjector.setMetaTag(A_PROPERTY, ANOTHER_VALUE);

        // then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(metaElement).setContent(captor.capture());
        verify(headElement, never()).insertFirst(any(Node.class));
        then(captor.getValue()).isEqualTo(ANOTHER_VALUE);
    }

    @Test
    public void injectTag_customTag_isInjected() {
        // given
        SeoElements seoElements = getBuilder().withMetaTag(A_PROPERTY, A_VALUE).build();

        // when
        tagsInjector.inject(seoElements);

        // then
        verify(headElement, times(DEFAULT_INJECTIONS + 1)).insertFirst(any(Node.class));
    }

    private MetaElement getMetaElementMock(String property, String content) {
        MetaElement mock = mock(MetaElement.class);
        when(mock.getName()).thenReturn(property);
        when(mock.getContent()).thenReturn(content);

        return mock;
    }

    private SeoElements.Builder getBuilder() {
        return new SeoElements.Builder();
    }
}
