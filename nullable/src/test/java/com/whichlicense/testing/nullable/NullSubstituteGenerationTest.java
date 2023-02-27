/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.nullable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import static com.whichlicense.testing.nullable.NullSubstituteProvider.nullTerminatedListOf;
import static com.whichlicense.testing.nullable.NullSubstituteProvider.uniqueSubstitutions;
import static java.util.Collections.singletonList;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

class NullSubstituteGenerationTest {
    @SuppressWarnings("unused")
    private static final Arguments ONE_ARG = Arguments.of(12);
    @SuppressWarnings("unused")
    private static final Arguments TWO_ARGS = Arguments.of(12, 21);
    @SuppressWarnings("unused")
    private static final Arguments THREE_ARGS = Arguments.of(12, 21, "test");

    @Test
    void givenOneParamWhenGeneratingUniqueSubstitutionsThenExactlyOneNullShouldBeReturned() {
        var arg = nullTerminatedListOf(ONE_ARG.get()[0]);
        var substitutions = uniqueSubstitutions(singletonList(arg));
        assertThat(substitutions).element(1).asList().containsNull();
    }

    @Test
    void givenTwoParamsWhenGeneratingUniqueSubstitutionsThenAllSubstitutionsContainNullValues() {
        var arg1 = nullTerminatedListOf(TWO_ARGS.get()[0]);
        var arg2 = nullTerminatedListOf(TWO_ARGS.get()[1]);
        var substitutions = uniqueSubstitutions(of(arg1, arg2));
        assertThat(substitutions.subList(1, substitutions.size()))
                .allMatch(l -> l.contains(null));
    }

    @Test
    void givenThreeParamsWhenGeneratingUniqueSubstitutionsThenAllSubstitutionsContainNullValues() {
        var arg1 = nullTerminatedListOf(THREE_ARGS.get()[0]);
        var arg2 = nullTerminatedListOf(THREE_ARGS.get()[1]);
        var arg3 = nullTerminatedListOf(THREE_ARGS.get()[2]);
        var substitutions = uniqueSubstitutions(of(arg1, arg2, arg3));
        assertThat(substitutions.subList(1, substitutions.size()))
                .allMatch(l -> l.contains(null));
    }

    @ParameterizedTest
    @NullSubstituteSource("ONE_ARG")
    void givenOneParamWhenReceivingSubstituteValuesThenExactlyOneNullShouldBeReceived(Integer i) {
        assertThat(i).isNull();
    }

    @ParameterizedTest
    @NullSubstituteSource("TWO_ARGS")
    void givenTwoParamsWhenReceivingSubstituteValuesThenAtLeastOneNullShouldBeReceived(Integer i, Integer x) {
        assertThat(new Object[]{i, x}).containsNull();
    }

    @ParameterizedTest
    @NullSubstituteSource("THREE_ARGS")
    void givenThreeParamsWhenReceivingSubstituteValuesThenAtLeastOneNullShouldBeReceived(Integer i, Integer x, String s) {
        assertThat(new Object[]{i, x, s}).containsNull();
    }
}
