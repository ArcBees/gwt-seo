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

import com.arcbees.seo.SeoElements;
import com.arcbees.seo.TagsInjector;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.RootPanel;

public class SeoWidgetGwtTest extends GWTTestCase {
    private static class TagsInjectorCaptor extends TagsInjector {
        private boolean isInjected;

        @Override
        public void inject(SeoElements seoElements) {
            super.inject(seoElements);

            isInjected = true;
        }

        public boolean isInjected() {
            return isInjected;
        }
    }

    private RootPanel mainPanel;

    @Override
    public String getModuleName() {
        return "com.arcbees.seo.Seo";
    }

    @Override
    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();

        mainPanel = RootPanel.get();
    }

    public void test_onAttach_injectsTags() {
        // given
        TagsInjectorCaptor injectorCaptor = new TagsInjectorCaptor();
        SeoWidget widget = new SeoWidget(injectorCaptor);

        // when
        mainPanel.add(widget);

        // then
        assertTrue(injectorCaptor.isInjected());
    }
}
