package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {
        // TodoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("My first Todo item").build();
        // TodoEntity 저장
        repository.save(entity);
        // TodoEntity 검색
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();

        //return "Test Service";
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        //validations
        validate(entity);

        repository.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> read(final String userId) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setUserId(userId);
        validate(todoEntity);

        List<TodoEntity> todoEntities = repository.findByUserId(userId);
        log.info("Read entity");

        return todoEntities;
    }

    public boolean update(final TodoEntity entity) {
        boolean result = false;

        validate(entity);
        try {
            TodoEntity todoEntity = repository.findByTodoId(entity.getId());
            todoEntity.setTitle(entity.getTitle());

            repository.save(todoEntity);

            result = true;
        } catch (Exception e) {
            log.info("update error");
            result = false;
        }

        return result;
    }

    public boolean delete(final TodoEntity entity) {
        boolean result = false;

        validate(entity);
        try {
            TodoEntity todoEntity = repository.findByTodoId(entity.getId());
            repository.delete(todoEntity);

            result = true;
        } catch (Exception e) {
            log.info("delete error");
            result = false;
        }

        return result;
    }

    // 검증부분은 다른 메서드에서도 계속 쓰일 예정이기 때문에 private method로 리팩토링
    private void validate(final TodoEntity entity) {
        if(entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
}
