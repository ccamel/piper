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
package com.creactiviti.piper.core.event;

import com.creactiviti.piper.core.DSL;
import com.creactiviti.piper.core.task.SimpleTaskLog;
import com.creactiviti.piper.core.task.TaskLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Arik Cohen
 * @since Apt 9, 2017
 */
public class TaskNotificationEventListener implements EventListener {

    private final TaskLogRepository taskLogRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public TaskNotificationEventListener(TaskLogRepository aTaskLogRepository) {
        taskLogRepository = aTaskLogRepository;
    }

    @Override
    public void onApplicationEvent(PiperEvent aEvent) {
        if (Events.TASK_NOTIFICATION.equals(aEvent.getType())) {
            String taskId = aEvent.getRequiredString(DSL.TASK_ID);
            String message = aEvent.getRequiredString(DSL.MESSAGE);
            Date time = aEvent.getCreateTime();

            SimpleTaskLog simpleTaskLog = SimpleTaskLog.createNew(taskId, time, message);

            taskLogRepository.create(simpleTaskLog);
        }
    }

}
