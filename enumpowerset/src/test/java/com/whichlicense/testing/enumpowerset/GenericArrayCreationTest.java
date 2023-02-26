/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.enumpowerset;

import com.whichlicense.testing.enumpowerset.PowersetGenerationTest.ThreeElementEnum;
import org.junit.jupiter.api.Test;

import static com.whichlicense.testing.enumpowerset.EnumPowersetProvider.toGenericArray;
import static com.whichlicense.testing.enumpowerset.PowersetGenerationTest.ThreeElementEnum.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class GenericArrayCreationTest {
    @Test
    void givenEnumClassTypeAndValuesWhenCallingToGenericArrayThenReturnedATypedAndSizedArrayWithTheProvidedValues() {
        var enumClass = ThreeElementEnum.class;
        var converter = toGenericArray(enumClass);

        ThreeElementEnum[] array = converter.apply(asList(A, B, C));

        assertThat(array).containsExactly(A, B, C);
    }
}
