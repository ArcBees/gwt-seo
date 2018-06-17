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

import java.util.LinkedHashMap;
import java.util.Map;

public class SeoElements {
    public static class Builder {
        private String title;
        private String description;
        private String keywords;
        private String fbAppId;
        private OpenGraph openGraph;
        private Image image;
        private TwitterCard twitterCard;
        private Map<String, String> customMetaTags;
        private String canonical;

        public Builder() {
            customMetaTags = new LinkedHashMap<>();
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withKeywords(String keywords) {
            this.keywords = keywords;
            return this;
        }

        public Builder withFbAppId(String fbAppId) {
            this.fbAppId = fbAppId;
            return this;
        }

        public Builder withOpenGraph(OpenGraph openGraph) {
            this.openGraph = openGraph;
            return this;
        }

        public Builder withImage(Image image) {
            this.image = image;
            return this;
        }

        public Builder withTwitterCard(TwitterCard twitterCard) {
            this.twitterCard = twitterCard;
            return this;
        }

        public Builder withMetaTag(String property, String content) {
            customMetaTags.put(property, content);
            return this;
        }

        public Builder withCanonical(String canonical) {
            this.canonical = canonical;
            return this;
        }

        public SeoElements build() {
            SeoElements seoElements = new SeoElements();

            seoElements.setTitle(title);
            seoElements.setDescription(description);
            seoElements.setKeywords(keywords);
            seoElements.setFbAppId(fbAppId);
            seoElements.setOpenGraph(openGraph);
            seoElements.setCustomMetaTags(customMetaTags);
            seoElements.setTwitterCard(twitterCard);
            seoElements.setImage(image);
            seoElements.setCanonical(canonical);

            return seoElements;
        }
    }

    private String title;
    private String description;
    private String keywords;
    private String fbAppId;
    private Image image;
    private OpenGraph openGraph;
    private TwitterCard twitterCard;
    private Map<String, String> customMetaTags;
    private String canonical;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFbAppId() {
        return fbAppId;
    }

    public void setFbAppId(String fbAppId) {
        this.fbAppId = fbAppId;
    }

    public OpenGraph getOpenGraph() {
        return openGraph;
    }

    public void setOpenGraph(OpenGraph openGraph) {
        this.openGraph = openGraph;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public TwitterCard getTwitterCard() {
        return twitterCard;
    }

    public void setTwitterCard(TwitterCard twitterCard) {
        this.twitterCard = twitterCard;
    }

    public Map<String, String> getCustomMetaTags() {
        return customMetaTags;
    }

    public void setCustomMetaTags(Map<String, String> customMetaTags) {
        this.customMetaTags = customMetaTags;
    }

    public String getCanonical() {
        return canonical;
    }

    public void setCanonical(String canonical) {
        this.canonical = canonical;
    }

    @Override
    public String toString() {
        return "SeoElements{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", keywords='" + keywords + '\'' +
                ", fbAppId='" + fbAppId + '\'' +
                ", image=" + image +
                ", openGraph=" + openGraph +
                ", twitterCard=" + twitterCard +
                ", customMetaTags=" + customMetaTags +
                ", canonical=" + canonical +
                '}';
    }
}
