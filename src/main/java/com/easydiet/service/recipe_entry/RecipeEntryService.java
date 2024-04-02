package com.easydiet.service.recipe_entry;

import com.easydiet.domain.OperationForbiddenException;
import com.easydiet.domain.authorization_service.AuthorizationService;
import com.easydiet.domain.authorization_service.Role;
import com.easydiet.domain.directory.Directory;
import com.easydiet.domain.directory.DirectoryRepository;
import com.easydiet.domain.recipe_entry.*;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeEntryService {
    private final RecipeEntryRepository recipeEntryRepository;
    private final DirectoryRepository directoryRepository;
    private final AuthorizationService authorizationService;

    @NotNull
    public RecipeEntry create(String directoryId, String name, String content, String userId)
    throws OperationForbiddenException {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);
        if (optionalDirectory.isEmpty()) {
            throw new OperationForbiddenException("Справочник с таким идентификатором не найден.");
        }
        String workspaceId = optionalDirectory.get().getWorkspaceId();

        List<Role> roles = authorizationService.getRoles(userId, workspaceId);
        if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
            throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может создать новую запись справочника рецептов.");
        }
        return recipeEntryRepository.saveAndFlush(
                RecipeEntry.create(
                        directoryId,
                        RecipeEntryName.create(name),
                        RecipeEntryContent.create(content),
                        workspaceId
                )
        );
    }

    public boolean rename(String id, String newName, String newContent, String userId)
            throws RecipeEntryNotFoundException, OperationForbiddenException {
        Optional<RecipeEntry> optionalRecipeEntry = recipeEntryRepository.findByIdentifier(id);

        if (optionalRecipeEntry.isEmpty()) {
            throw new RecipeEntryNotFoundException(id);
        }
        else {
            RecipeEntry recipeEntry = optionalRecipeEntry.get();
            boolean result = recipeEntry.rename(
                    RecipeEntryName.create(newName),
                    RecipeEntryContent.create(newContent)
            );
            String workspaceId = optionalRecipeEntry.get().getWorkspaceId();

            List<Role> roles = authorizationService.getRoles(userId, workspaceId);
            if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
                throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может изменять название записи справочника рецептов.");
            }
            recipeEntryRepository.save(recipeEntry);
            return result;
        }
    }

    public boolean delete(String id, String userId) throws  OperationForbiddenException {
        Optional<RecipeEntry> optionalRecipeEntry = recipeEntryRepository
                .findByIdentifier(id);
        if (optionalRecipeEntry.isEmpty()) {
            return false;
        }
        else {
            RecipeEntry recipeEntry = optionalRecipeEntry.get();
            boolean result = recipeEntry.delete();
            String workspaceId = optionalRecipeEntry.get().getWorkspaceId();

            List<Role> roles = authorizationService.getRoles(userId, workspaceId);
            if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
                throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может удалить запись справочника рецептов.");
            }
            recipeEntryRepository.save(recipeEntry);
            return result;
        }
    }

    public List<RecipeEntry> list(String directoryId, String userId) throws OperationForbiddenException {
            Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);
        if (optionalDirectory.isEmpty()) {
            throw new OperationForbiddenException("Справочник с таким идентификатором не найден.");
        }
        String workspaceId = optionalDirectory.get().getWorkspaceId();
        List<Role> roles = authorizationService.getRoles(userId, workspaceId);
        if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
            throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может просматривать список рецептов в справочнике.");
        }
        Collection<RecipeEntry> recipeEntries = recipeEntryRepository.listAllByDirectoryId(directoryId);
        if (recipeEntries.isEmpty()) {
            throw new OperationForbiddenException("Справочник пуст.");
        }

        return recipeEntryRepository.listAllByDirectoryId(directoryId).stream()
                .filter(RecipeEntry -> !RecipeEntry.isDeleted())
                .toList();
    }

    public RecipeEntry details(String id, String userId) throws OperationForbiddenException, RecipeEntryNotFoundException {
        Optional<RecipeEntry> optionalRecipeEntry = recipeEntryRepository.findByIdentifier(id);
        if (optionalRecipeEntry.isEmpty()) {
            throw new RecipeEntryNotFoundException(id);
        } else {
            String workspaceId = optionalRecipeEntry.get().getWorkspaceId();

            List<Role> roles = authorizationService.getRoles(userId, workspaceId);
            if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
                throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может просматривать детали записи справочника рецептов.");
            }
            return optionalRecipeEntry.get();
        }
    }
    


}
