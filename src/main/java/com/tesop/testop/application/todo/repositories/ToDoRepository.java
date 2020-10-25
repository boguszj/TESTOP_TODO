package com.tesop.testop.application.todo.repositories;

import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.entitiy.ToDoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, ToDoId> {

    Optional<ToDo> findByToDoId(ToDoId toDoId);

}
