package com.easydiet.service.group_entry;

import com.easydiet.domain.OperationForbiddenException;
import com.easydiet.domain.authorization_service.AuthorizationService;
import com.easydiet.domain.authorization_service.Role;
import com.easydiet.domain.directory.Directory;
import com.easydiet.domain.directory.DirectoryRepository;
import com.easydiet.domain.group_entry.*;
import lombok.AllArgsConstructor;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupEntryService {

    private final GroupEntryRepository groupEntryRepository;
    private final DirectoryRepository directoryRepository;
    private final AuthorizationService authorizationService;

    @NotNull
    public GroupEntry create(String directoryId, String name, String type, String description, String userId)
    throws OperationForbiddenException {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);
        if (optionalDirectory.isEmpty()) {
            throw new OperationForbiddenException("Справочник с таким идентификатором не найден.");
        }
        String workspaceId = optionalDirectory.get().getWorkspaceId();

        List<Role> roles = authorizationService.getRoles(userId, workspaceId);
        if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
            throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может создать новую запись справочника ингредиентов.");
        }
        GroupEntry result = GroupEntry.create(
                directoryId,
                GroupEntryName.create(name),
                GroupEntryType.create(type),
                GroupEntryDescription.create(description),
                workspaceId
        );
        groupEntryRepository.saveAndFlush(result);
        return result;
    }

    public boolean rename(String id, String newName)
            throws GroupEntryNotFoundException {
        Optional<GroupEntry> optionalGroupEntry = groupEntryRepository.findByIdentifier(id);
        if (optionalGroupEntry.isEmpty()) {
            throw new GroupEntryNotFoundException((id));
        } else {
            GroupEntry groupEntry = optionalGroupEntry.get();
            boolean result = groupEntry.rename(
                    GroupEntryName.create(newName)
            );
            groupEntryRepository.save(groupEntry);
            return result;
        }
    }

    public boolean delete(String id) {
        Optional<GroupEntry> optionalGroupEntry = groupEntryRepository.findByIdentifier(id);
        if (optionalGroupEntry.isEmpty()) {
            return false;
        } else {
            GroupEntry groupEntry = optionalGroupEntry.get();
            boolean result = groupEntry.delete();
            groupEntryRepository.save(groupEntry);
            return result;
        }
    }

    public List<GroupEntry> list() {
        return groupEntryRepository.listAll().stream()
                .filter(GroupEntry -> !GroupEntry.isDeleted())
                .toList();
    }

    public List<GroupEntry> list(String directoryId) {
        return groupEntryRepository.listAllByDirectoryId(directoryId).stream()
                .filter(GroupEntry -> !GroupEntry.isDeleted())
                .toList();
    }

    public GroupEntry findById(String id) {
        Optional<GroupEntry> optionalGroupEntry = groupEntryRepository.findById(id);
        if (optionalGroupEntry.isEmpty()) {
            throw new IllegalStateException();
        }
        return optionalGroupEntry.get();
    }
}


