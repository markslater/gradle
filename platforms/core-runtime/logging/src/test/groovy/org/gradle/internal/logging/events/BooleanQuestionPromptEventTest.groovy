/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.internal.logging.events

import spock.lang.Specification

class BooleanQuestionPromptEventTest extends Specification {
    def "accepts valid input"() {
        def event = new BooleanQuestionPromptEvent(123, "question?", true, "true")

        expect:
        def result = event.convert(input)
        result.left.get() == expected
        !result.right.isPresent()

        where:
        input      | expected
        'yes'      | true
        'y'        | true
        'Y'        | true
        'YES'      | true
        'yes   '   | true
        ' y   '    | true
        'no'       | false
        'n'        | false
        'N'        | false
        '   no   ' | false
        '   n   '  | false
    }

    def "uses default value on empty input"() {
        def event = new BooleanQuestionPromptEvent(123, "question?", true, "true")

        expect:
        def result = event.convert("")
        result.left.get() == true
        !result.right.isPresent()
    }

    def "rejects invalid input"() {
        def event = new BooleanQuestionPromptEvent(123, "question?", true, "yes")

        expect:
        def result = event.convert(input)
        !result.left.isPresent()
        result.right.get() == "Please enter 'yes' or 'no' (default: 'yes'): "

        where:
        input  | _
        'bla'  | ''
        'nope' | ''
        'yep'  | ''
    }
}
