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

package com.arcbees.seo.widget;

import com.arcbees.seo.Image;
import com.arcbees.seo.OpenGraph;
import com.arcbees.seo.SeoElements;
import com.arcbees.seo.TagsInjector;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class SeoWidget extends ContainerNode implements AttachEvent.Handler {
    private final Widget widget;
    private final SeoElements.Builder seoBuilder;
    private final TagsInjector tagsInjector;

    public SeoWidget() {
        this(new TagsInjector());
    }

    public SeoWidget(TagsInjector tagsInjector) {
        widget = new SimplePanel();
        widget.getElement().getStyle().setDisplay(Style.Display.NONE);
        widget.addAttachHandler(this);

        this.seoBuilder = new SeoElements.Builder();
        this.tagsInjector = tagsInjector;
    }

    public void add(Title title) {
        seoBuilder.withTitle(title.getText());
    }

    public void add(Og og) {
        OpenGraph.Builder builder = new OpenGraph.Builder();

        OgImage image = og.getImage();
        if (image != null) {
            builder.withImage(new Image(image.getText(), image.getHeight(), image.getWidth(), image.getMimeType()));
        }

        OgType ogType = og.getOgType();
        if (ogType != null) {
            builder.withType(ogType.getValue());
        }

        seoBuilder.withOpenGraph(builder.build());
    }

    public void add(Description description) {
        seoBuilder.withDescription(description.getText());
    }

    public void add(Keywords keywords) {
        seoBuilder.withKeywords(keywords.getText());
    }

    public void add(FbAppId fbAppId) {
        seoBuilder.withFbAppId(fbAppId.getText());
    }

    @Override
    public void onAttachOrDetach(AttachEvent event) {
        if (event.isAttached()) {
            tagsInjector.inject(seoBuilder.build());
        }
    }

    @Override
    public Widget asWidget() {
        return widget;
    }
}
