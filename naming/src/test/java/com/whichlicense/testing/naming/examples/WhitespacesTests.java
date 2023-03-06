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

class WhitespacesTests {
    @Test
    void givenWhitespacesWhenCallingIsBlankOnStringThenTrueShouldBeReturned(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given whitespaces, when calling "
                + "is blank on string, then true should be returned");
    }

    @Test
    void givenEntryWhenCreatingNamedInstanceFromEntryThenKeyAndValueCorrespondToNameAndParam(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given entry, when creating named instance "
                + "from entry, then key and value correspond to name and param");
    }
}
