package com.tesop.testop.application.todo.handlers;

import com.tesop.testop.application.todo.mappers.ToDoMapper;
import com.tesop.testop.application.todo.repositories.ToDoRepository;
import com.tesop.testop.application.todo.providers.ToDoProvider;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.entitiy.ToDoId;
import com.tesop.testop.domain.todo.commandDtos.ToDoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PatchToDoHandler {

    private final ToDoProvider toDoProvider;
    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    public void handle(ToDoId toDoId, ToDoDto toDoDto) {
        ToDo toDo = toDoProvider.findOneById(toDoId);
        toDoMapper.updateToDo(toDoDto, toDo);
        toDoRepository.save(toDo);
    }

}
