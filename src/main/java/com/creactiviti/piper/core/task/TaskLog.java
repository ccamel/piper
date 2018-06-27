/*
 * Copyright 2016-2018 the original author or authors.
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
package com.creactiviti.piper.core.task;

import java.util.Date;


public interface TaskLog {

  /**
   * Get the unique id of the task log.
   *
   * @return String the id
   */
  String getId();

  /**
   * Get the id of the task execution for which this task
   * belongs to.
   *
   * @return String the id of the job
   */
  String getTaskExecutionId();

  /**
   * Get the time of the log.
   *
   * @return Date
   */
  Date getTime();

  /**
   * Get the message of the log.
   *
   * @return The message of the log.
   */
  String getMessage();

}
