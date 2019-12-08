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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Product gender enumeration.
 */
public enum Gender {

    MALE(1, "Male"),
    FEMALE(2, "Female"),
    BOY(3, "Boy"),
    GIRL(4, "Girl"),
    ADULT_UNISEX(5, "Unisex"),
    CHILD_UNISEX(6, "Unisex");

    private static final Map<Integer, Gender> LOOKUP = new HashMap<>();

    static {
        EnumSet.allOf(Gender.class).forEach(gender -> LOOKUP.put(gender.getValue(), gender));
    }

    private final int value;
    private final String label;

    Gender(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static Gender get(int value) {
        return LOOKUP.get(value);
    }
}
