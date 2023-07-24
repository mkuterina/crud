package com.easydiet.domain.group_entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GroupEntryId implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Override
    public String toString() {
        return "GroupEntryId(" + id + ")";
    }

    public static GroupEntryId create() {
        return new GroupEntryId(UUID.randomUUID().toString());
    }

    public static GroupEntryId create(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalStateException("Идентификатор не может быть пустым.");
        }
        return new GroupEntryId(s);
    }
}
