/**
 * Copyright 2019 Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.etag.service.data.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Product Stockkeeping Unit.
 */
public class Sku {

    private String skuId;
    private boolean active;
    private String colorwayId;
    private String colorway;
    private String size;
    private Map<String, Prices> prices = new HashMap<>();

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getColorwayId() {
        return colorwayId;
    }

    public void setColorwayId(String colorwayId) {
        this.colorwayId = colorwayId;
    }

    public String getColorway() {
        return colorway;
    }

    public void setColorway(String colorway) {
        this.colorway = colorway;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Map<String, Prices> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, Prices> prices) {
        this.prices = prices;
    }
}
