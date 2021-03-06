/*
 * Copyright 2018 the original author or authors.
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

package org.gradle.internal.build;

import org.gradle.internal.invocation.BuildController;

import java.util.function.Function;

/**
 * A stand alone nested build, which is a nested build that runs as part of some containing build as a single atomic step, without participating in task execution of the containing build.
 */
public interface StandAloneNestedBuild extends NestedBuildState {
    /**
     * Runs a single invocation of this build, executing the given action and returning the result. Should be called once only for a given build instance.
     */
    <T> T run(Function<? super BuildController, T> buildAction);
}
