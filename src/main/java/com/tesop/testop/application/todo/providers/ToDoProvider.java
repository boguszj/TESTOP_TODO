package com.tesop.testop.application.todo.providers;

import com.tesop.testop.application.todo.repositories.ToDoRepository;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.entitiy.ToDoId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ToDoProvider {

    private final ToDoRepository toDoRepository;

    public ToDo findOneById(ToDoId toDoId) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(toDoId);
        return optionalToDo.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
