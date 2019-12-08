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
package example.etag.service.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import example.etag.service.core.ProductDateFormat;
import example.etag.service.data.model.Product;

/**
 * Response object returned when requesting product information.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "typeId",
        "type",
        "active",
        "shortName",
        "longName",
        "description",
        "startDate",
        "endDate",
        "genderId",
        "gender"
})
public class GetProductResponse {

    /**
     * Builds a {@link GetProductResponse} from a {@link Product} object returned
     * from the product service.
     *
     * @param product product information
     * @return an instance of {@link GetProductResponse}
     */
    public static GetProductResponse from(Product product) {
        GetProductResponse response = new GetProductResponse();
        response.setId(product.getProductId());
        response.setTypeId(product.getProductType().getValue());
        response.setType(product.getProductType().toString());
        response.setActive(product.isActive());
        response.setShortName(product.getShortName());
        response.setLongName(product.getLongName());
        response.setDescription(product.getDescription());
        response.setStartDate(ProductDateFormat.format(product.getStartTime()));
        response.setEndDate(ProductDateFormat.format(product.getEndTime()));
        response.setGenderId(product.getGender().getValue());
        response.setGender(product.getGender().getLabel());

        return response;
    }

    private String id;
    @JsonProperty("type_id") private Integer typeId;
    private String type;
    private boolean active;
    @JsonProperty("short_name") private String shortName;
    @JsonProperty("long_name") private String longName;
    private String description;
    @JsonProperty("start_date") private String startDate;
    @JsonProperty("end_date") private String endDate;
    @JsonProperty("gender_id") private Integer genderId;
    private String gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
