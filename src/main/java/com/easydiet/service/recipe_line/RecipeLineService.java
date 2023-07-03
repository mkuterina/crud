package com.easydiet.service.recipe_line;

import com.easydiet.domain.directory.DirectoryId;
import com.easydiet.domain.recipe_entry.RecipeEntryId;
import com.easydiet.domain.recipe_line.RecipeLine;
import com.easydiet.domain.recipe_line.RecipeLineRepository;
import com.easydiet.domain.recipe_line.RecipeLineType;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeLineService {

    private final RecipeLineRepository recipeLineRepository;

    @Autowired
    public RecipeLineService(RecipeLineRepository recipeLineRepository) {
        this.recipeLineRepository = recipeLineRepository;
    }

    @NotNull
    public RecipeLine create(String recipe_entry_id, String directory_id, String type) {
        return recipeLineRepository.saveAndFlush(
                RecipeLine.create(RecipeEntryId.create(recipe_entry_id),
                        DirectoryId.create(directory_id),
                        RecipeLineType.create(type))
        );
    }

    public boolean retype(String id, String newType)
        throws RecipeLineNotFoundException {

        Optional<RecipeLine> optionalRecipeLine = recipeLineRepository.findByIdentifier(id);
        if (optionalRecipeLine.isEmpty()) {
            throw new RecipeLineNotFoundException(id);
        } else {
            RecipeLine recipeLine = optionalRecipeLine.get();
            boolean result = recipeLine.retype(
                    RecipeLineType.create(newType)
            );
            recipeLineRepository.save(recipeLine);
            return result;
        }
    }

    public boolean delete(String id) {
        Optional<RecipeLine> optionalRecipeLine = recipeLineRepository
                .findByIdentifier(id);
        if (optionalRecipeLine.isEmpty()) {
            return false;
        } else {
            RecipeLine recipeLine = optionalRecipeLine.get();
            boolean result = recipeLine.delete();
            recipeLineRepository.save(recipeLine);
            return result;
        }
    }

    public List<RecipeLine> list() {
        return recipeLineRepository.listAll().stream()
                .filter(RecipeLine -> RecipeLine.isDeleted())
                .toList();
    }

    public RecipeLine details(String id) throws RecipeLineNotFoundException {
        Optional<RecipeLine> optionalRecipeLine = recipeLineRepository.findByIdentifier(id);
        if (optionalRecipeLine.isEmpty()) {
            throw new RecipeLineNotFoundException(id);
        } else {
            return optionalRecipeLine.get();
        }
    }

    public List<RecipeLine> list(String recipeEntryId) {
        return recipeLineRepository.listAllByRecipeEntryId(recipeEntryId).stream()
                .filter(RecipeLine -> RecipeLine.isDeleted())
                .toList();
    }
}
