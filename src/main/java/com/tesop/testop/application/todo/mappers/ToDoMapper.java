package com.tesop.testop.application.todo.mappers;

import com.tesop.testop.domain.todo.commandDtos.ToDoDto;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ToDoMapper {

    void updateToDo(ToDoDto toDoDto, @MappingTarget ToDo toDo);

}
