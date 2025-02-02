/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2022 the original author or authors.
 */
package org.assertj.core.error;

import static java.lang.String.format;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldContainSubsequenceOfCharSequence.shouldContainSubsequence;

import org.assertj.core.description.TextDescription;
import org.assertj.core.internal.ComparatorBasedComparisonStrategy;
import org.assertj.core.presentation.StandardRepresentation;
import org.assertj.core.util.CaseInsensitiveStringComparator;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link ShouldContainSubsequenceOfCharSequence#create(org.assertj.core.description.Description, org.assertj.core.presentation.Representation)}</code>.
 *
 * @author Joel Costigliola
 */
class ShouldContainSubsequenceOfCharSequence_create_Test {

  @Test
  void should_create_error_message() {
    // GIVEN
    String[] sequenceValues = { "{", "author", "title", "}" };
    String actual = "{ 'title':'A Game of Thrones', 'author':'George Martin'}";
    ErrorMessageFactory factory = shouldContainSubsequence(actual, sequenceValues, 1);
    // WHEN
    String message = factory.create(new TextDescription("Test"), new StandardRepresentation());
    // THEN
    then(message).isEqualTo(format("[Test] %nExpecting actual:%n" +
                                   "  \"" + actual + "\"%n" +
                                   "to contain the following CharSequences in this order:%n" +
                                   "  [\"{\", \"author\", \"title\", \"}\"]%n" +
                                   "but \"title\" was found before \"author\"%n"));
  }

  @Test
  void should_create_error_message_with_custom_comparison_strategy() {
    // GIVEN
    String[] sequenceValues = { "{", "author", "title", "}" };
    String actual = "{ 'title':'A Game of Thrones', 'author':'George Martin'}";
    ErrorMessageFactory factory = shouldContainSubsequence(actual, sequenceValues, 1,
                                                           new ComparatorBasedComparisonStrategy(CaseInsensitiveStringComparator.instance));
    // WHEN
    String message = factory.create(new TextDescription("Test"), new StandardRepresentation());
    // THEN
    then(message).isEqualTo(format("[Test] %nExpecting actual:%n" +
                                   "  \"" + actual + "\"%n" +
                                   "to contain the following CharSequences in this order:%n" +
                                   "  [\"{\", \"author\", \"title\", \"}\"]%n" +
                                   "but \"title\" was found before \"author\"%n" +
                                   "when comparing values using CaseInsensitiveStringComparator"));
  }

}
