package com.tesop.testop.todo;

import com.tesop.testop.application.todo.handlers.CreateToDoHandler;
import com.tesop.testop.application.todo.repositories.ToDoRepository;
import com.tesop.testop.domain.todo.commandDtos.ToDoDto;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.statuses.ToDoStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

public class CreateToDoHandlerTest {

    @Mock
    ToDoRepository toDoRepository;

    @InjectMocks
    CreateToDoHandler createToDoHandler;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateToDoWithoutStatus() {
        ToDoDto toDoDto = new ToDoDto("name", null, null, null, null);

        createToDoHandler.handle(toDoDto);

        verify(toDoRepository).save(argThat((ToDo toDo) -> toDo.getName().equals(toDoDto.getName())
                    && toDo.getDescription() == null
                    && toDo.getImportance() == null
                    && toDo.getDeadline() == null
                    && toDo.getToDoStatus().equals(ToDoStatus.OPEN)
        ));
    }

    @Test
    public void shouldCreateToDoWithStatus() {
        ToDoDto toDoDto = new ToDoDto("name", null, ToDoStatus.DONE, null, null);

        createToDoHandler.handle(toDoDto);

        verify(toDoRepository).save(argThat((ToDo toDo) -> toDo.getName().equals(toDoDto.getName())
                && toDo.getDescription() == null
                && toDo.getImportance() == null
                && toDo.getDeadline() == null
                && toDo.getToDoStatus().equals(toDoDto.getToDoStatus())
        ));
    }
}
