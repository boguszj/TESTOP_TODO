package com.tesop.testop.domain.todo.entitiy;

import com.tesop.testop.domain.todo.statuses.ToDoStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@Table(name = "to_do")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDo {

    @Getter
    @NonNull
    @EmbeddedId
    ToDoId toDoId;

    @NonNull
    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "to_do_status")
    ToDoStatus toDoStatus;

    @Column(name = "importance")
    Integer importance;

    @Column(name = "deadline")
    Instant deadline;

    public ToDo(String name, String description, ToDoStatus toDoStatus, Integer importance, Instant deadline) {
        this.toDoId = ToDoId.newId();
        this.name = name;
        this.description = description;
        this.toDoStatus = toDoStatus != null ? toDoStatus : ToDoStatus.OPEN;
        this.importance = importance;
        this.deadline = deadline;
    }
}
