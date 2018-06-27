package com.creactiviti.piper.core.taskhandler.misc;

import com.creactiviti.piper.core.event.EventPublisher;
import com.creactiviti.piper.core.event.Events;
import com.creactiviti.piper.core.event.PiperEvent;
import com.creactiviti.piper.core.task.Task;
import com.creactiviti.piper.core.task.TaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Exception implements TaskHandler<Object> {

    @Autowired
    private EventPublisher workerEventPublisher;

    @Override
    public Object handle(Task aTask) throws InterruptedException {
        String id = aTask.getRequiredString("id");

        Map<String, Object> ctx = new HashMap<>();
        ctx.put("taskId", id);
        ctx.put("message", "Hello world");

        workerEventPublisher.publishEvent(PiperEvent.of(Events.TASK_NOTIFICATION, ctx));

        Thread.sleep(3000);

        ctx.put("taskId", id);
        ctx.put("message", "Hello world 2");

        workerEventPublisher.publishEvent(PiperEvent.of(Events.TASK_NOTIFICATION, ctx));

        throw new IllegalArgumentException(aTask.getRequiredString("message"));
    }
}
