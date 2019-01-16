/*
 * Copyright © 2015 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package co.cask.cdap.etl.api;

import co.cask.cdap.api.annotation.Beta;
import co.cask.cdap.etl.api.validation.SchemaPropagator;

/**
 * Allows the stage to configure pipeline.
 */
@Beta
public interface PipelineConfigurable extends SchemaPropagator<StageConfigurer> {

  /**
   * Configure a pipeline, declaring what datasets and plugins will be needed when the pipeline actually runs.
   * This is called exactly once, when a pipeline is deployed.
   *
   * @param pipelineConfigurer the configurer used to add required datasets and plugins
   * @throws IllegalArgumentException if the given config is invalid
   */
  void configurePipeline(PipelineConfigurer pipelineConfigurer);

}
