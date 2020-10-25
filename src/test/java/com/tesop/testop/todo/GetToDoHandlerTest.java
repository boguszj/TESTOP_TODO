package com.tesop.testop.todo;

import com.tesop.testop.application.todo.handlers.GetToDoHandler;
import com.tesop.testop.application.todo.providers.ToDoProvider;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.entitiy.ToDoId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

public class GetToDoHandlerTest {

    @Mock
    ToDoProvider toDoProvider;

    @InjectMocks
    GetToDoHandler getToDoHandler;

    ToDo toDo = new ToDo("name", null, null, null, null);

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetToDo() {
        getToDoHandler.handle(toDo.getToDoId());

        verify(toDoProvider).findOneById(argThat((ToDoId toDoId) -> toDoId.equals(toDo.getToDoId())));
    }
}
