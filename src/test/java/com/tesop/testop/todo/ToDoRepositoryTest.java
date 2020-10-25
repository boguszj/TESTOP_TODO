package com.tesop.testop.todo;

import com.tesop.testop.application.todo.repositories.ToDoRepository;
import com.tesop.testop.domain.todo.entitiy.ToDo;
import com.tesop.testop.domain.todo.statuses.ToDoStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToDoRepositoryTest {

    @Autowired
    ToDoRepository toDoRepository;

    @Test
    @Transactional
    @Rollback
    public void testCreateToDo() {
        ToDo toDo = new ToDo("name", "desc", ToDoStatus.DONE, 0, Instant.EPOCH);

        toDoRepository.save(toDo);

        assertNotNull(toDoRepository.findAll().get(0));
    }

    @Test
    @Transactional
    @Rollback
    public void testFindByToDoId() {
        ToDo toDo = new ToDo("name", "desc", ToDoStatus.DONE, 0, Instant.EPOCH);
        toDoRepository.save(toDo);

        Optional<ToDo> optionalToDo = toDoRepository.findByToDoId(toDo.getToDoId());
        assertNotNull(optionalToDo.get());
        ToDo foundToDo = optionalToDo.get();

        assertEquals(toDo.getDescription(), foundToDo.getDescription());
        assertEquals(toDo.getImportance(), foundToDo.getImportance());
        assertEquals(toDo.getToDoStatus(), foundToDo.getToDoStatus());
        assertEquals(toDo.getDeadline(), foundToDo.getDeadline());
        assertEquals(toDo.getToDoId(), foundToDo.getToDoId());
        assertEquals(toDo.getName(), foundToDo.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteToDo() {
        ToDo toDo = new ToDo("name", "desc", ToDoStatus.DONE, 0, Instant.EPOCH);
        ToDo savedToDo = toDoRepository.save(toDo);

        toDoRepository.delete(savedToDo);

        List<ToDo> toDos = toDoRepository.findAll();
        assertEquals(0, toDos.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAll() {
        List.of(
                new ToDo("name1", "desc1", ToDoStatus.DONE, 0, Instant.EPOCH),
                new ToDo("name2", "desc2", ToDoStatus.DONE, 0, Instant.EPOCH),
                new ToDo("name1", "desc1", ToDoStatus.DONE, 0, Instant.EPOCH),
                new ToDo("name2", "desc2", ToDoStatus.DONE, 0, Instant.EPOCH)
        ).forEach(t -> toDoRepository.save(t));

        List<ToDo> toDos = toDoRepository.findAll();

        assertEquals(4, toDos.size());

    }

    @Test
    @Transactional
    @Rollback
    public void testNameUniqueness() {
        ToDo toDo = new ToDo("name", "desc", ToDoStatus.DONE, 0, Instant.EPOCH);

        toDoRepository.save(toDo);
        toDoRepository.save(toDo);

        assertNotNull(toDoRepository.findAll().get(0));
    }

}
