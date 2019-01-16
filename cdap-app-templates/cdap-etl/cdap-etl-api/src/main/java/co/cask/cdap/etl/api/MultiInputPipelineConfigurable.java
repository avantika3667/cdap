/*
 * Copyright © 2016 Cask Data, Inc.
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
 * Allows the stage with multiple inputs to configure pipeline.
 */
@Beta
public interface MultiInputPipelineConfigurable extends SchemaPropagator<MultiInputStageConfigurer> {
  /**
   * Configure an ETL pipeline, adding datasets and plugins that the stage needs.
   *
   * @param multiInputPipelineConfigurer the configurer used to add required datasets and plugins
   * @throws IllegalArgumentException if the given config is invalid
   */
  void configurePipeline(MultiInputPipelineConfigurer multiInputPipelineConfigurer) throws IllegalArgumentException;

}
