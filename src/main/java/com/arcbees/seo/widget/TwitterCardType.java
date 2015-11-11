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

import com.google.gwt.uibinder.client.UiConstructor;

public class TwitterCardType extends BaseNode {
    public enum TypeValue {
        SUMMARY("summary"),
        SUMMARY_LARGE("summary_large_image"),
        APP("app"),
        PLAYER("player");

        private final String value;

        TypeValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private final TypeValue typeValue;

    @UiConstructor
    public TwitterCardType(TypeValue typeValue) {
        this.typeValue = typeValue;
    }

    public TypeValue getValue() {
        return typeValue;
    }
}
