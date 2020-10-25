package com.tesop.testop.todo;

import com.tesop.testop.application.todo.handlers.DeleteToDoHandler;
import com.tesop.testop.application.todo.providers.ToDoProvider;
import com.tesop.testop.application.todo.repositories.ToDoRepository;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.entitiy.ToDoId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteToDoHandlerTest {

    @Mock
    ToDoRepository toDoRepository;

    @Mock
    ToDoProvider toDoProvider;

    @InjectMocks
    DeleteToDoHandler deleteToDoHandler;

    ToDo toDo = new ToDo("name", null, null, null, null);

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(toDoProvider.findOneById(Mockito.any(ToDoId.class))).thenReturn(toDo);
    }

    @Test
    public void shouldDeleteToDo() {
        deleteToDoHandler.handle(toDo.getToDoId());

        verify(toDoRepository).delete(argThat((ToDo toDo) -> toDo.getToDoId().equals(this.toDo.getToDoId())));
    }
}
