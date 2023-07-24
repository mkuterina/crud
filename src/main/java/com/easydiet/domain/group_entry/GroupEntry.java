package com.easydiet.domain.group_entry;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
@ToString
@Entity
@Table(name = "group_entry")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GroupEntry {

    @Column(name = "directory_id")
    private String directoryId;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "group_name")
    @Convert(converter = GroupEntryNameConverter.class)
    private GroupEntryName name;

    @Column(name = "group_type")
    @Convert(converter = GroupEntryTypeConverter.class)
    private GroupEntryType type;

    @Column(name = "group_description")
    @Convert(converter = GroupEntryDescriptionConverter.class)
    private GroupEntryDescription description;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "group_status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;


    public static GroupEntry create(
            String directoryId,
            GroupEntryName name,
            GroupEntryType type,
            GroupEntryDescription description
    ) {
         if (name == null) {
            throw new IllegalStateException("Имя ингридиента должно быть задано");
        }
        return new GroupEntry(
                directoryId,
                GroupEntryId.create().getId(),
                name,
                type,
                description,
                LocalDateTime.now(),
                null,
                EntityStatus.ENABLED
        );
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
    public boolean isDeleted() {
        return deleteDate != null;
    }

    public boolean rename(GroupEntryName newName) {
        if (newName == null) {
            throw new IllegalStateException("Имя группы должно быть задано.");
        }
        if (deleteDate != null) {
            return false;
        } else if (this.name.equals(newName)) {
            return false;
            }
        else {
            this.name = newName;
            return true;
        }
    }
}

