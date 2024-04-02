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
            throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может создать новую запись справочника ингредиентов.");
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

    public boolean rename(String id, String newName, String newContent)
            throws RecipeEntryNotFoundException {
        Optional<RecipeEntry> optionalRecipeEntry = recipeEntryRepository.findByIdentifier(id);

        if (optionalRecipeEntry.isEmpty()) {
            throw new RecipeEntryNotFoundException(id);
        }
        else {
            RecipeEntry recipeEntry = optionalRecipeEntry.get();
            boolean result = recipeEntry.rename(RecipeEntryName.create(newName), RecipeEntryContent.create(newContent));
            recipeEntryRepository.save(recipeEntry);
            return result;
        }
    }

    public boolean delete(String id) {
        Optional<RecipeEntry> optionalRecipeEntry = recipeEntryRepository
                .findByIdentifier(id);
        if (optionalRecipeEntry.isEmpty()) {
            return false;
        }
        else {
            RecipeEntry recipeEntry = optionalRecipeEntry.get();
            boolean result = recipeEntry.delete();
            recipeEntryRepository.save(recipeEntry);
            return result;
        }
    }

    public List<RecipeEntry> list() {
        return recipeEntryRepository.listAll().stream()
                .filter(recipeEntry -> !recipeEntry.isDeleted())
                .toList();
    }

    public RecipeEntry details(String id) throws RecipeEntryNotFoundException {
        Optional<RecipeEntry> optionalRecipeEntry = recipeEntryRepository.findByIdentifier(id);
        if (optionalRecipeEntry.isEmpty()) {
            throw new RecipeEntryNotFoundException(id);
        } else {
            return optionalRecipeEntry.get();
        }
    }
    
     public List<RecipeEntry> list(String directoryId) {
        return recipeEntryRepository.listAllByDirectoryId(directoryId).stream()
                .filter(RecipeEntry -> !RecipeEntry.isDeleted())
                .toList();
    }

}
