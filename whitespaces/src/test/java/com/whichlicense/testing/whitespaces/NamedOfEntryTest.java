/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.whitespaces;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap.SimpleEntry;

import static org.assertj.core.api.Assertions.assertThat;

class NamedOfEntryTest {
    @Test
    void givenEntryWhenCreatingNamedInstanceFromEntryThenKeyAndValueCorrespondToNameAndParam() {
        var entry = new SimpleEntry<>("key", "value");
        var named = WhitespacesProvider.namedOf(entry);
        assertThat(named).extracting(Named::getName).isEqualTo("key");
        assertThat(named).extracting(Named::getPayload).isEqualTo("value");
    }
}
