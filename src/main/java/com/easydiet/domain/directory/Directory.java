package com.easydiet.domain.directory;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Id;
import java.time.LocalDateTime;

@ToString
@Entity
@Table(name = "directory")
public class Directory {

        @Id
        @Column(name = "directory_id")
        private String directory_id;

        @Column(name = "directory_name")
        private String name;

        @Column(name = "directory_type")
        private String type;

        @lombok.Getter
        @Column(name = "directory_description")
        private String description;

        @Column(name = "create_date")
        private LocalDateTime createDate;

        @Column(name = "delete_date")
        private LocalDateTime deleteDate;

        @Column(name = "directory_status")
        @Convert(converter = EntityStatusConverter.class)
        private EntityStatus status;

      public Directory() {
        }
    public String getDirectory_id() {
         return directory_id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
    //
    // IngredientOperations Support
    //

    protected Directory(DirectoryId directory_id, DirectoryName name, DirectoryType type, DirectoryDescription description, LocalDateTime createDate) {
        this.directory_id = directory_id.getDirectory_id();
        this.name = name.getName();
        this.type = type.getType();
        this.description = description.getDescription();
        this.createDate = createDate;
        this.status = EntityStatus.ENABLED;
    }

    //
    // Domain Logic
    //

    public boolean isDeleted() {
        return deleteDate != null;
    }

    public boolean rename(DirectoryName newName, DirectoryType type, DirectoryDescription description) {
        if (newName == null) {
            throw new IllegalStateException("Имя справочника должно быть задано");
        }

        if (deleteDate != null) {
            return false;
        }
        else if (this.name.equals(newName.getName())) {
            return false;
        }
        else {
            this.name = newName.getName();
            return true;
        }
    }

    public boolean delete() {
        if (deleteDate != null) {
            return false;
        }
        else {
            this.deleteDate = LocalDateTime.now();
            return true;
        }
    }
}
