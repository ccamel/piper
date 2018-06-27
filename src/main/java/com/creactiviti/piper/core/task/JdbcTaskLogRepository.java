package com.creactiviti.piper.core.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class JdbcTaskLogRepository implements TaskLogRepository {
    private NamedParameterJdbcOperations jdbc;
    private ObjectMapper json = new ObjectMapper();
    private CounterRepository counterRepository;

    @Override
    public void create(TaskLog aTaskLog) {
        SqlParameterSource sqlParameterSource = createSqlParameterSource(aTaskLog);

        jdbc.update("insert into task_log (id,task_execution_id,time,message,log_number) values (:id,:taskExecutionId,:time,:message,:logNumber)", sqlParameterSource);
    }

    private SqlParameterSource createSqlParameterSource (TaskLog aTaskLog) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();

        sqlParameterSource.addValue("id", aTaskLog.getId());
        sqlParameterSource.addValue("taskExecutionId", aTaskLog.getTaskExecutionId());
        sqlParameterSource.addValue("time", aTaskLog.getTime());
        sqlParameterSource.addValue("message", aTaskLog.getMessage());
        sqlParameterSource.addValue("logNumber", counterRepository.increment(aTaskLog.getTaskExecutionId()));

        return sqlParameterSource;
    }

    public void setJdbcOperations (NamedParameterJdbcOperations aJdbcOperations) {
        jdbc = aJdbcOperations;
    }

    public void setObjectMapper (ObjectMapper aJson) {
        json = aJson;
    }

    public void setCounterRepository (CounterRepository aCounterRepository) {
        counterRepository = aCounterRepository;
    }
}
