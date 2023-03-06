/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.naming.examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.assertj.core.api.Assertions.assertThat;

class NullableTests {
    @Test
    void givenOneParamWhenGeneratingUniqueSubstitutionsThenExactlyOneNullShouldBeReturned(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given one param, when generating unique "
                + "substitutions, then exactly one null should be returned");
    }

    @Test
    void givenTwoParamsWhenGeneratingUniqueSubstitutionsThenAllSubstitutionsContainNullValues(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given two params, when generating unique "
                + "substitutions, then all substitutions contain null values");
    }

    @Test
    void givenThreeParamsWhenGeneratingUniqueSubstitutionsThenAllSubstitutionsContainNullValues(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given three params, when generating unique "
                + "substitutions, then all substitutions contain null values");
    }

    @Test
    void givenOneParamWhenReceivingSubstituteValuesThenExactlyOneNullShouldBeReceived(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given one param, when receiving substitute "
                + "values, then exactly one null should be received");
    }

    @Test
    void givenTwoParamsWhenReceivingSubstituteValuesThenAtLeastOneNullShouldBeReceived(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given two params, when receiving substitute "
                + "values, then at least one null should be received");
    }

    @Test
    void givenThreeParamsWhenReceivingSubstituteValuesThenAtLeastOneNullShouldBeReceived(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given three params, when receiving substitute "
                + "values, then at least one null should be received");
    }
}
