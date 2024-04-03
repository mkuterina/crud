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
    public Directory create(String name, String type, String description, String userId, String workspaceId)
              throws OperationForbiddenException {
        Optional<Directory> optionalDirectory = directoryRepository.findByName(name);
        if (optionalDirectory.isPresent()) {
            throw new OperationForbiddenException("Справочник с таким именем уже существует.");
        }

        List<Role> roles = authorizationService.getRoles(userId, workspaceId);
        if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
            throw new OperationForbiddenException("Пользователь с таким уровнем доступа не может создать новый справочник.");
        }
        return directoryRepository.save(
                Directory.create(
                       name,
                       type,
                       description,
                       workspaceId
                )
        );
    }

    public boolean rename(String directoryId, String newName, String newType, String newDescription, String userId)
            throws DirectoryNotFoundException, OperationForbiddenException {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);

        if (optionalDirectory.isEmpty()) {
            throw new DirectoryNotFoundException(directoryId);
        }
        else {
            Directory directory = optionalDirectory.get();
            boolean result = directory.rename(
                    DirectoryName.create(newName),
                    DirectoryType.create(newType),
                    DirectoryDescription.create(newDescription)
            );
            String workspaceId = optionalDirectory.get().getWorkspaceId();

            List<Role> roles = authorizationService.getRoles(userId, workspaceId);
            if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
                throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может изменять название справочника.");
            }
            directoryRepository.save(directory);
            return result;
        }
    }
    public boolean delete(String directoryId, String userId) throws  OperationForbiddenException {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);
        if (optionalDirectory.isEmpty()) {
            return false;
        }
        else {
            Directory directory = optionalDirectory.get();
            boolean result = directory.delete();

            String workspaceId = optionalDirectory.get().getWorkspaceId();

            List<Role> roles = authorizationService.getRoles(userId, workspaceId);
            if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
                throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может удалить справочник.");
            }
            directoryRepository.save(directory);
            return result;
        }
    }
    //TODO: Посмотреть метод list
    public List<Directory> list(String userId) throws OperationForbiddenException {
        List<Directory> directoryList = directoryRepository.listAll();
        if (directoryList.isEmpty()) {
            throw new OperationForbiddenException("Список справочников пуст.");
        }
        String workspaceId = directoryList.get(0).getWorkspaceId();
        List<Role> roles = authorizationService.getRoles(userId, workspaceId);
        if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
            throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может просматривать список ингредиентов в справочнике.");
        }
        return directoryRepository.listAll().stream()
                .filter(directory -> !directory.isDeleted())
                .toList();
    }

    public Directory details(String directoryId, String userId) throws DirectoryNotFoundException, OperationForbiddenException {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);
        if (optionalDirectory.isEmpty()) {
            throw new DirectoryNotFoundException(directoryId);
        }
        else {
            String workspaceId = optionalDirectory.get().getWorkspaceId();
            List<Role> roles = authorizationService.getRoles(userId, workspaceId);
            if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
                throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может просматривать детали справочника.");
            }
            return optionalDirectory.get();
        }
    }
}
