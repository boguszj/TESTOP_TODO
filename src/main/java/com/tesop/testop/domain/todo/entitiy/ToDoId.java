package com.tesop.testop.domain.todo.entitiy;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ToDoId implements Serializable {

    @NonNull
    @Column(name = "to_do_id")
    UUID uuid;

    public ToDoId(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public static ToDoId newId() {
        return new ToDoId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}
