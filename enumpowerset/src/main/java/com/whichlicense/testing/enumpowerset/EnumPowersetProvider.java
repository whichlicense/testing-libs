/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.enumpowerset;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.reflect.Array.newInstance;
import static java.util.Comparator.comparing;
import static java.util.EnumSet.noneOf;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

/**
 * This class generates a powerset of all possible combinations of the enum values of a given enum type.
 * <p>
 * It implements the {@link ArgumentsProvider} and {@link AnnotationConsumer} interfaces for use in JUnit 5 tests.
 *
 * @author David Greven
 * @version 0
 * @since 0.0.0
 */
public class EnumPowersetProvider implements ArgumentsProvider, AnnotationConsumer<EnumPowersetSource> {
    /**
     * The enum class whose powerset is to be generated.
     */
    private Class<? extends Enum<?>> clazz;

    /**
     * This method generates the powerset of a given enum class.
     *
     * @param enumClass the class of the enum whose powerset is to be generated.
     * @param <E>       the type of the enum.
     * @return the powerset of the enum.
     * @since 0.0.0
     */
    static <E extends Enum<E>> Set<EnumSet<E>> generatePowerset(Class<E> enumClass) {
        var values = enumClass.getEnumConstants();
        return range(0, 1 << values.length)
                .mapToObj(i -> range(0, values.length)
                        .filter(j -> (i & (1 << j)) != 0)
                        .mapToObj(j -> values[j])
                        .collect(toCollection(() -> noneOf(enumClass))))
                .collect(toSet());
    }

    /**
     * This method converts a collection of objects of type T to an array of objects of type R.
     *
     * @param arrayClass the class of the array to be created.
     * @param <T>        the type of the objects in the collection.
     * @param <R>        the type of the objects in the resulting array.
     * @return a function that converts a collection of objects of type T to an array of objects of type R.
     * @since 0.0.0
     */
    @SuppressWarnings("unchecked")
    static <T, R extends T> Function<? super Collection<T>, R[]> toGenericArray(Class<T> arrayClass) {
        return c -> c.toArray(capacity -> (R[]) newInstance(arrayClass, capacity));
    }

    /**
     * This method sets the enum class to be used for generating the powerset.
     *
     * @param source the annotation specifying the enum class.
     * @since 0.0.0
     */
    @Override
    public void accept(EnumPowersetSource source) {
        clazz = source.value();
    }

    /**
     * This method generates a stream of all possible combinations of the enum values and converts them to arrays.
     *
     * @param <E> the type of the enum.
     * @return a stream of arrays containing all possible combinations of the enum values.
     * @since 0.0.0
     */
    @SuppressWarnings("unchecked")
    <E extends Enum<E>> Stream<E[]> streamCombinations() {
        return generatePowerset((Class<E>) clazz).stream().map(toGenericArray((Class<E>) clazz));
    }

    /**
     * This method generates a sorted stream of JUnit Jupiter arguments containing all possible combinations of the
     * enum values as arrays.
     *
     * @param context the current extension context for the test; never {@code null}
     * @return a stream of JUnit arguments containing all possible combinations of the enum values as arrays.
     * @since 0.0.0
     */
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return streamCombinations().sorted(comparing(e -> e.length)).map(e -> Arguments.of((Object) e));
    }
}
