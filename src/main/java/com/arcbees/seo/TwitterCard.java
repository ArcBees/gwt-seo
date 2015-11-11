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

import com.arcbees.seo.widget.TwitterCardType;

public class TwitterCard {
    public static class Builder {
        private String card;
        private String site;

        public Builder withCard(TwitterCardType.TypeValue card) {
            this.card = card.getValue();
            return this;
        }

        public Builder withSite(String site) {
            this.site = site;
            return this;
        }

        public TwitterCard build() {
            TwitterCard twitterCard = new TwitterCard();
            twitterCard.setCard(card);
            twitterCard.setSite(site);

            return twitterCard;
        }
    }

    private String card;
    private String site;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
