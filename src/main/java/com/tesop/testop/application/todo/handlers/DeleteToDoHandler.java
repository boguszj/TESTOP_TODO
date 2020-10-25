package com.tesop.testop.application.todo.handlers;

import com.tesop.testop.application.todo.repositories.ToDoRepository;
import com.tesop.testop.application.todo.providers.ToDoProvider;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.entitiy.ToDoId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteToDoHandler {

    private final ToDoProvider toDoProvider;
    private final ToDoRepository toDoRepository;

    public void handle(ToDoId toDoId) {
        ToDo toDo = toDoProvider.findOneById(toDoId);
        toDoRepository.delete(toDo);
    }
}
