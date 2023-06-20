package com.easydiet.api.rest.recipe_entry;

import com.easydiet.domain.recipe_entry.RecipeEntry;
import com.easydiet.service.recipe_entry.RecipeEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("recipe_entry")
public class RecipeEntryController {
    private final RecipeEntryService recipeEntryService;

    @Autowired
    public RecipeEntryController(RecipeEntryService recipeEntryService) {
        this.recipeEntryService = recipeEntryService;
     }

     @GetMapping("deprecated")
     public ListRecipeEntriesQueryResult list() {
        try {
            List<RecipeEntry> recipeEntries = recipeEntryService.list();
            return ListRecipeEntriesQueryResult.success(recipeEntries);
        } catch (Exception e) {
            return ListRecipeEntriesQueryResult.fail(e.getMessage());
        }
     }

     @GetMapping
     public ListRecipeEntriesQueryResult list(
        @RequestParam(value = "directory_id", required = false) String directoryId) {
         try {
             if (directoryId == null || directoryId.isBlank()) {
                 List<RecipeEntry> recipeEntries = recipeEntryService.list();
                 return ListRecipeEntriesQueryResult.success(recipeEntries);
             } else {
                 List<RecipeEntry> recipeEntries = recipeEntryService.list(directoryId);
                 return ListRecipeEntriesQueryResult.success(recipeEntries);
             }
         } catch (Exception e) {
             return ListRecipeEntriesQueryResult.fail(e.getMessage());

         }
     }

    @GetMapping("{id}")
    public RecipeEntryDetailsQueryResult details(@PathVariable("id") String id) {
        try {
            RecipeEntry recipeEntry = recipeEntryService.details(id);
            return RecipeEntryDetailsQueryResult.success(recipeEntry);
        }
        catch (Exception e) {
            return RecipeEntryDetailsQueryResult.fail(e.getMessage());
        }
    }

    @PostMapping
    public CreateRecipeEntryCommandResult create(@RequestBody CreateRecipeEntryCommand createRecipeEntryCommand) {
        try {
            RecipeEntry recipeEntry = recipeEntryService.create(createRecipeEntryCommand.getDirectory_id(),
                    createRecipeEntryCommand.getName(),
                    createRecipeEntryCommand.getContent());
            return CreateRecipeEntryCommandResult.success(recipeEntry);
        }
        catch (Exception e) {
            return CreateRecipeEntryCommandResult.fail(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public DeleteRecipeEntryCommandResult delete(@PathVariable("id") String id) {
        try {
            boolean result = recipeEntryService.delete(id);
            return DeleteRecipeEntryCommandResult.success(result);
        }
        catch (Exception e) {
            return DeleteRecipeEntryCommandResult.fail(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public RenameRecipeEntryCommandResult rename(@PathVariable("id") String id,
             @RequestBody RenameRecipeEntryCommand renameRecipeEntryCommand) {
        try {
            boolean result = recipeEntryService.rename(id, renameRecipeEntryCommand.getNewName(),
                    renameRecipeEntryCommand.getNewContent());
            return RenameRecipeEntryCommandResult.success(result);
        }
        catch (Exception e) {
            return RenameRecipeEntryCommandResult.fail(e.getMessage());
        }
    }
}
