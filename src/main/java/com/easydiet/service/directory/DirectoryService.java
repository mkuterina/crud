package com.easydiet.service.directory;

import com.easydiet.domain.OperationForbiddenException;
import com.easydiet.domain.authorization_service.AuthorizationService;
import com.easydiet.domain.authorization_service.Role;
import com.easydiet.domain.directory.*;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectoryService {

    private final DirectoryRepository directoryRepository;
    private final AuthorizationService authorizationService;

      @NotNull
    public Directory create(String name, String type, String description, String userId, String workspaceId) throws OperationForbiddenException {
        Optional<Directory> optionalDirectory = directoryRepository.findByName(name);
        if (optionalDirectory.isPresent()) {
            throw new OperationForbiddenException("Справочник с таким именем уже существует.");
        }

        List<Role> roles = authorizationService.getRoles(userId, workspaceId);
        if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
            throw new OperationForbiddenException("Пользователь с таким уровнем доступа не может создать новый справочник.");
        }
        return directoryRepository.save(
                DirectoryOperations.create(
                        DirectoryName.create(name),
                        DirectoryType.create(type),
                        DirectoryDescription.create(description),
                        workspaceId
                )
        );
    }
    public boolean rename(String directoryId, String newName, String newType, String newDescription)
            throws DirectoryNotFoundException {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);

        if (optionalDirectory.isEmpty()) {
            throw new DirectoryNotFoundException(directoryId);
        }
        else {
            Directory directory = optionalDirectory.get();
            boolean result = directory.rename(
                    DirectoryName.create(newName), DirectoryType.create(newType), DirectoryDescription.create(newDescription)
            );
            directoryRepository.save(directory);
            return result;
        }
    }
    public boolean delete(String directoryId) {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);
        if (optionalDirectory.isEmpty()) {
            return false;
        }
        else {
            Directory directory = optionalDirectory.get();
            boolean result = directory.delete();
            directoryRepository.save(directory);
            return result;
        }
    }
    public List<Directory> list() {
        return directoryRepository.listAll().stream()
                .filter(directory -> !directory.isDeleted())
                .toList();
    }
    public Directory details(String directoryId) throws DirectoryNotFoundException {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);
        if (optionalDirectory.isEmpty()) {
            throw new DirectoryNotFoundException(directoryId);
        }
        else {
            return optionalDirectory.get();
        }
    }
}
