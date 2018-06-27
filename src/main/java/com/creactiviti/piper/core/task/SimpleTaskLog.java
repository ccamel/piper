package com.creactiviti.piper.core.task;

import com.creactiviti.piper.core.MapObject;
import com.creactiviti.piper.core.uuid.UUIDGenerator;

import java.util.Date;

public class SimpleTaskLog extends MapObject implements TaskLog {

    private SimpleTaskLog() {
        super();
    }

    @Override
    public String getId() {
        return this.getRequiredString("id");
    }

    @Override
    public String getTaskExecutionId() {
        return this.getRequiredString("taskExecutionId");
    }

    @Override
    public Date getTime() {
        return this.getDate("time");
    }

    @Override
    public String getMessage() {
        return this.getRequiredString("message");
    }

    public static SimpleTaskLog createNew(String taskExecutionId, Date time, String message) {
        SimpleTaskLog simpleTaskLog = new SimpleTaskLog();

        simpleTaskLog.set("id", UUIDGenerator.generate());
        simpleTaskLog.set("taskExecutionId", taskExecutionId);
        simpleTaskLog.set("time", time);
        simpleTaskLog.set("message", message);

        return simpleTaskLog;
    }
}
