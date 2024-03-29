package com.easydiet.domain.directory;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Id;
import java.time.LocalDateTime;

@ToString
@Entity
@Getter
@NoArgsConstructor
@Table(name = "directory")
public class Directory {

        @Id
        @Column(name = "directory_id")
        private String directoryId;

        @Column(name = "directory_name")
        private String name;

        @Column(name = "directory_type")
        private String type;

        @Column(name = "directory_description")
        private String description;

        @Column(name = "create_date")
        private LocalDateTime createDate;

        @Column(name = "delete_date")
        private LocalDateTime deleteDate;

        @Column(name = "directory_status")
        @Convert(converter = EntityStatusConverter.class)
        private EntityStatus status;

        @Column(name = "workspace_id")
        private String workspaceId;


    //
    // DirectoryOperations Support
    //

    protected Directory(DirectoryId directoryId, DirectoryName name, DirectoryType type, DirectoryDescription description, LocalDateTime createDate, String workspaceId) {
        this.directoryId = directoryId.getDirectoryId();
        this.name = name.getName();
        this.type = type.getType();
        this.description = description.getDescription();
        this.createDate = createDate;
        this.status = EntityStatus.ENABLED;
        this.workspaceId = getWorkspaceId();
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
