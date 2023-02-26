/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.enumpowerset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.whichlicense.testing.enumpowerset.EnumPowersetProvider.generatePowerset;
import static com.whichlicense.testing.enumpowerset.PowersetGenerationTest.ThreeElementEnum.*;
import static java.util.EnumSet.noneOf;
import static java.util.EnumSet.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PowersetGenerationTest {
    @Test
    void givenEmptyEnumWhenGeneratingPowersetThenAnEmptySetShouldBeReturned() {
        var powerset = generatePowerset(EmptyEnum.class);
        assertThat(powerset).containsExactly(noneOf(EmptyEnum.class));
    }

    @Test
    void givenOneElementEnumWhenGeneratingPowersetThenAllValidSetsShouldBeReturned() {
        var powerset = generatePowerset(OneElementEnum.class);

        assertThat(powerset).containsExactlyInAnyOrder(
                noneOf(OneElementEnum.class), of(OneElementEnum.A)
        );
    }

    @Test
    void givenTwoElementEnumWhenGeneratingPowersetThenAllValidSetsShouldBeReturned() {
        var powerset = generatePowerset(TwoElementEnum.class);

        assertThat(powerset).containsExactlyInAnyOrder(
                noneOf(TwoElementEnum.class), of(TwoElementEnum.A),
                of(TwoElementEnum.B), of(TwoElementEnum.A, TwoElementEnum.B)
        );
    }

    @Test
    void givenThreeElementEnumWhenGeneratingPowersetThenAllValidSetsShouldBeReturned() {
        var powerset = generatePowerset(ThreeElementEnum.class);

        assertThat(powerset).containsExactlyInAnyOrder(
                noneOf(ThreeElementEnum.class), of(A), of(B),
                of(A, B), of(C), of(A, C), of(B, C), of(A, B, C)
        );
    }

    @Test
    void givenEnumPowersetSourceForEmptyEnumWhenCountingTheStreamOfAllValidSetsThenOneShouldBeReturned(@Mock EnumPowersetSource source) {
        var enumClass = EmptyEnum.class;
        var provider = new EnumPowersetProvider();

        when(source.value()).then(ignored -> enumClass);

        provider.accept(source);

        assertThat(provider.streamCombinations().count()).isEqualTo(1);
    }

    @Test
    void givenEnumPowersetSourceForOneElementEnumWhenCountingTheStreamOfAllValidSetsThenTwoShouldBeReturned(@Mock EnumPowersetSource source) {
        var enumClass = OneElementEnum.class;
        var provider = new EnumPowersetProvider();

        when(source.value()).then(ignored -> enumClass);

        provider.accept(source);

        assertThat(provider.streamCombinations().count()).isEqualTo(2);
    }

    @Test
    void givenEnumPowersetSourceForTwoElementEnumWhenCountingTheStreamOfAllValidSetsThenFourShouldBeReturned(@Mock EnumPowersetSource source) {
        var enumClass = TwoElementEnum.class;
        var provider = new EnumPowersetProvider();

        when(source.value()).then(ignored -> enumClass);

        provider.accept(source);

        assertThat(provider.streamCombinations().count()).isEqualTo(4);
    }

    @Test
    void givenEnumPowersetSourceForThreeElementEnumWhenCountingTheStreamOfAllValidSetsThenEightShouldBeReturned(@Mock EnumPowersetSource source) {
        var enumClass = ThreeElementEnum.class;
        var provider = new EnumPowersetProvider();

        when(source.value()).then(ignored -> enumClass);

        provider.accept(source);

        assertThat(provider.streamCombinations().count()).isEqualTo(8);
    }


    enum EmptyEnum {
    }

    enum OneElementEnum {
        A
    }

    enum TwoElementEnum {
        A, B
    }

    enum ThreeElementEnum {
        A, B, C
    }
}
