package com.example.demo.persistence;

import com.example.demo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    // ?1은 메서드의 매개변수의 순서 위치다.
    @Query("select t from TodoEntity t where t.userId = ?1")
    List<TodoEntity> findByUserId(String userId);

    @Query("select t from TodoEntity t where t.id = :id")
    TodoEntity findByTodoId(String id);

    @Query("select t from TodoEntity t where t.title = :title")
    TodoEntity findByTitle(String title);

}
