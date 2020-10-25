package com.tesop.testop.api;

import com.tesop.testop.application.todo.handlers.CreateToDoHandler;
import com.tesop.testop.application.todo.handlers.DeleteToDoHandler;
import com.tesop.testop.application.todo.handlers.GetToDoHandler;
import com.tesop.testop.application.todo.handlers.PatchToDoHandler;
import com.tesop.testop.application.todo.repositories.ToDoRepository;
import com.tesop.testop.domain.todo.commandDtos.ToDoDto;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.entitiy.ToDoId;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/todo")
public class ToDoResource {

    private final ToDoRepository toDoRepository;
    private final GetToDoHandler getToDoHandler;
    private final CreateToDoHandler createToDoHandler;
    private final DeleteToDoHandler deleteToDoHandler;
    private final PatchToDoHandler patchToDoHandler;

    /*
    Temporary until accounts implemented
     */
    @GetMapping(path = "/")
    public List<ToDo> getAll() {
        return toDoRepository.findAll();
    }

    @GetMapping(path = "/{toDoId}")
    public ToDo getOne(@PathVariable ToDoId toDoId) {
        return getToDoHandler.handle(toDoId);
    }

    @PostMapping
    public ToDoId createOne(@Valid @RequestBody ToDoDto toDoDto) {
        return createToDoHandler.handle(toDoDto);
    }

    @DeleteMapping(path = "/{toDoId}")
    public void deleteOne(@PathVariable ToDoId toDoId) {
        deleteToDoHandler.handle(toDoId);
    }

    @PatchMapping(path = "/{toDoId}")
    public void updateOne(@PathVariable ToDoId toDoId, @RequestBody ToDoDto toDoDto) {
        patchToDoHandler.handle(toDoId, toDoDto);
    }

}
