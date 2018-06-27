package com.creactiviti.piper.core.taskhandler.misc;

import com.creactiviti.piper.core.task.Task;
import com.creactiviti.piper.core.task.TaskHandler;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Foo implements TaskHandler<Object> {
    @Override
    public Object handle(Task aTask) throws InterruptedException {
        Map<String, String> results = new HashMap<>();
        int n = RandomUtils.nextInt(5, 10);

        for(int i = 0; i < n; i++) {
            results.put("key-"+i, "The value was "+RandomUtils.nextInt(0, 10000));
        }


        return results;
    }
}
