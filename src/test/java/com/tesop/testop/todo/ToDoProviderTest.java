package com.tesop.testop.todo;

import com.tesop.testop.application.todo.providers.ToDoProvider;
import com.tesop.testop.application.todo.repositories.ToDoRepository;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.entitiy.ToDoId;
import com.tesop.testop.domain.todo.statuses.ToDoStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

public class ToDoProviderTest {

    @Mock
    ToDoRepository toDoRepository;

    @InjectMocks
    ToDoProvider toDoProvider;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldResponseStatusExceptionWithNotFoundHttpStatus() {
        when(toDoRepository.findById(Mockito.any(ToDoId.class))).thenReturn(Optional.empty());

        ResponseStatusException thrown = assertThrows(
                ResponseStatusException.class,
                () -> toDoProvider.findOneById(ToDoId.newId())
        );

        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
    }

    @Test
    public void shouldFindToDo() {
        ToDo toDo = new ToDo("name", "desc", ToDoStatus.DONE, 0, Instant.EPOCH);
        when(toDoRepository.findById(Mockito.any(ToDoId.class))).thenReturn(Optional.of(toDo));
        toDoRepository.save(toDo);

        ToDo foundToDo = toDoProvider.findOneById(toDo.getToDoId());

        assertEquals(toDo.getDescription(), foundToDo.getDescription());
        assertEquals(toDo.getImportance(), foundToDo.getImportance());
        assertEquals(toDo.getToDoStatus(), foundToDo.getToDoStatus());
        assertEquals(toDo.getDeadline(), foundToDo.getDeadline());
        assertEquals(toDo.getToDoId(), foundToDo.getToDoId());
        assertEquals(toDo.getName(), foundToDo.getName());
    }
}
