package com.tesop.testop.application.todo.handlers;

import com.tesop.testop.application.todo.providers.ToDoProvider;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.entitiy.ToDoId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetToDoHandler {

    private final ToDoProvider toDoProvider;

    public ToDo handle(ToDoId toDoId) {
        return toDoProvider.findOneById(toDoId);
    }

}
