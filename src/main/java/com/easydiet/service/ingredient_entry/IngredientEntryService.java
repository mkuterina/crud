package com.easydiet.service.ingredient_entry;

import com.easydiet.domain.OperationForbiddenException;
import com.easydiet.domain.authorization_service.AuthorizationService;
import com.easydiet.domain.authorization_service.Role;
import com.easydiet.domain.directory.Directory;
import com.easydiet.domain.directory.DirectoryId;
import com.easydiet.domain.directory.DirectoryRepository;
import com.easydiet.domain.ingredient_entry.*;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientEntryService {
    private final IngredientEntryRepository ingredientEntryRepository;
    private final DirectoryRepository directoryRepository;
    private final AuthorizationService authorizationService;

    @NotNull
    public IngredientEntry create(String directoryId, String name, String description, String userId)
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
        return ingredientEntryRepository.saveAndFlush(
                IngredientEntry.create(
                        DirectoryId.create(directoryId),
                        IngredientEntryName.create(name),
                        IngredientEntryDescription.create(description),
                        workspaceId
                )
        );
    }

    public boolean rename(String id, String newName, String newDescription, String userId)
            throws IngredientEntryNotFoundException {

        Optional<IngredientEntry> optionalIngredientEntry = ingredientEntryRepository
                .findByIdentifier(id);

        if (optionalIngredientEntry.isEmpty()) {
            throw new IngredientEntryNotFoundException(id);
        } else {
            IngredientEntry ingredientEntry = optionalIngredientEntry.get();
            boolean result = ingredientEntry.rename(
                    IngredientEntryName.create(newName),
                    IngredientEntryDescription.create(newDescription)
            );
            String workspaceId = optionalIngredientEntry.get().getWorkspaceId();

            List<Role> roles = authorizationService.getRoles(userId, workspaceId);
            if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
                throw new IngredientEntryNotFoundException("Пользовтель с таким уровнем доступа не может изменять название записи справочника ингредиентов.");
            }
            ingredientEntryRepository.save(ingredientEntry);
            return result;
        }
    }

    public boolean delete(String id, String userId) throws OperationForbiddenException {
        Optional<IngredientEntry> optionalIngredientEntry = ingredientEntryRepository.findByIdentifier(id);

        if (optionalIngredientEntry.isEmpty()) {
            return false;
        } else {
            IngredientEntry ingredientEntry = optionalIngredientEntry.get();
            boolean result = ingredientEntry.delete();

            String workspaceId = optionalIngredientEntry.get().getWorkspaceId();

            List<Role> roles = authorizationService.getRoles(userId, workspaceId);
            if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
                throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может удалить запись справочника ингредиентов.");
            }
            ingredientEntryRepository.save(ingredientEntry);
            return result;
        }
    }

    public List<IngredientEntry> list(String directoryId, String userId) throws OperationForbiddenException {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);
        if (optionalDirectory.isEmpty()) {
            throw new OperationForbiddenException("Справочник с таким идентификатором не найден.");
        }

        String workspaceId = optionalDirectory.get().getWorkspaceId();
        List<Role> roles = authorizationService.getRoles(userId, workspaceId);
        if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
            throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может просматривать список ингредиентов в справочнике.");
        }

        Collection<IngredientEntry> ingredientEntries = ingredientEntryRepository.listAllByDirectoryId(directoryId);
        if (ingredientEntries.isEmpty()) {
            throw new OperationForbiddenException("Справочник пуст.");
        }

        return ingredientEntryRepository.listAllByDirectoryId(directoryId).stream()
                .filter(IngredientEntry -> !IngredientEntry.isDeleted())
                .toList();
    }
    public IngredientEntry details(String id) throws IngredientEntryNotFoundException {
        Optional<IngredientEntry> optionalIngredientEntry = ingredientEntryRepository.findByIdentifier(id);
        if (optionalIngredientEntry.isEmpty()) {
            throw new IngredientEntryNotFoundException(id);
            } else {
            return optionalIngredientEntry.get();
            }
        }
}

