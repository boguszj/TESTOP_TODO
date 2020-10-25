package com.tesop.testop.application.todo.handlers;

import com.tesop.testop.application.todo.repositories.ToDoRepository;
import com.tesop.testop.domain.todo.commandDtos.ToDoDto;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.entitiy.ToDoId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateToDoHandler {

    private final ToDoRepository toDoRepository;

    public ToDoId handle(ToDoDto toDoDto) {
        ToDo toDo = new ToDo(
                toDoDto.getName(),
                toDoDto.getDescription(),
                toDoDto.getToDoStatus(),
                toDoDto.getImportance(),
                toDoDto.getDeadline());
        toDoRepository.save(toDo);
        return toDo.getToDoId();
    }
}
