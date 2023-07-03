package com.easydiet.service.recipe_entry;

import com.easydiet.domain.directory.DirectoryId;
import com.easydiet.domain.entity_link.EntityLink;
import com.easydiet.domain.recipe_entry.*;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeEntryService {
    private final RecipeEntryRepository recipeEntryRepository;

    @Autowired
    public RecipeEntryService(RecipeEntryRepository recipeEntryRepository) {
        this.recipeEntryRepository = recipeEntryRepository;
    }

    @NotNull
    public RecipeEntry create(String directory_id, String name, String content) {
        return recipeEntryRepository.saveAndFlush(
                RecipeEntryOperations.create(
                        DirectoryId.create(directory_id),
                        RecipeEntryName.create(name),
                        RecipeEntryContent.create(content)
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
