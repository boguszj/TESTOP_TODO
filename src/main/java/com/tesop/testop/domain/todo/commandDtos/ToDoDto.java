package com.tesop.testop.domain.todo.commandDtos;

import com.tesop.testop.domain.todo.statuses.ToDoStatus;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Value
public class ToDoDto {

    @NotNull
    String name;
    String description;
    ToDoStatus toDoStatus;
    Instant deadline;
    Integer importance;

}
