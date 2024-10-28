package com.easydiet.domain.directory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class DirectoryId implements Serializable {

    @Id
    @Column(name = "directory_id")
    private final String directoryId;
    private DirectoryId(String directoryId) {
        this.directoryId = directoryId;
    }

    @Override
    public String toString() {
        return "DirectoryId(" + directoryId + ")";
    }

    public static DirectoryId create() {
        return new DirectoryId(UUID.randomUUID().toString());
    }

    public static DirectoryId create(String s) {

        if (s == null || s.isBlank()) {
            throw new IllegalStateException("Идентификатор не может быть пустым.");
        }
        return new DirectoryId(s);
    }
}