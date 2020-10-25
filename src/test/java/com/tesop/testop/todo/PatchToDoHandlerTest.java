package com.tesop.testop.todo;

import com.tesop.testop.application.todo.handlers.PatchToDoHandler;
import com.tesop.testop.application.todo.mappers.ToDoMapper;
import com.tesop.testop.application.todo.mappers.ToDoMapperImpl;
import com.tesop.testop.application.todo.providers.ToDoProvider;
import com.tesop.testop.application.todo.repositories.ToDoRepository;
import com.tesop.testop.domain.todo.commandDtos.ToDoDto;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.entitiy.ToDoId;
import com.tesop.testop.domain.todo.statuses.ToDoStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PatchToDoHandlerTest {

    @Mock
    ToDoRepository toDoRepository;

    @Mock
    ToDoProvider toDoProvider;

    @Spy
    ToDoMapper toDoMapper = new ToDoMapperImpl();

    @InjectMocks
    PatchToDoHandler patchToDoHandler;

    ToDo toDo = new ToDo("name", null, null, null, null);

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(toDoProvider.findOneById(Mockito.any(ToDoId.class))).thenReturn(toDo);
    }

    @Test
    public void shouldPatchSingleField() {
        String newName = "newName";
        ToDoDto toDoDto = new ToDoDto(newName, null, null, null, null);

        patchToDoHandler.handle(toDo.getToDoId(), toDoDto);

        verify(toDoRepository).save(argThat((ToDo toDo) -> toDo.getToDoId().equals(this.toDo.getToDoId())
                    && toDo.getName().equals(newName)
                    && toDo.getDescription() == null
                    && toDo.getImportance() == null
                    && toDo.getDeadline() == null
                    && toDo.getToDoStatus().equals(ToDoStatus.OPEN)
        ));
    }
}
