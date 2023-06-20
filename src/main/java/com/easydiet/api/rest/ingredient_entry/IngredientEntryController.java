package com.easydiet.api.rest.ingredient_entry;

import com.easydiet.domain.ingredient_entry.IngredientEntry;
import com.easydiet.service.ingredient_entry.IngredientEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ingredient_entry")
public class IngredientEntryController {
    private final IngredientEntryService ingredientEntryService;

    @Autowired
    public IngredientEntryController(IngredientEntryService ingredientEntryService) {
        this.ingredientEntryService = ingredientEntryService;
    }

    @GetMapping("deprecated")
    public ListIngredientEntriesQueryResult list() {
        try {
            List<IngredientEntry> ingredientEntries = ingredientEntryService.list();
            return ListIngredientEntriesQueryResult.success(ingredientEntries);
        } catch (Exception e) {
            return ListIngredientEntriesQueryResult.fail(e.getMessage());
        }
    }


    @GetMapping
    public ListIngredientEntriesQueryResult list(
            @RequestParam(value = "directory_id", required = false) String directoryId) {
        try {
            if (directoryId == null || directoryId.isBlank()) {
                List<IngredientEntry> ingredientEntries = ingredientEntryService.list(directoryId);
                return ListIngredientEntriesQueryResult.success(ingredientEntries);
            } else {
                List<IngredientEntry> ingredientEntries = ingredientEntryService.list(directoryId);
                return ListIngredientEntriesQueryResult.success(ingredientEntries);
            }
        } catch (Exception e) {
            return ListIngredientEntriesQueryResult.fail(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public IngredientEntryDetailsQueryResult details(@PathVariable("id") String id) {
        try {
            IngredientEntry ingredientEntry = ingredientEntryService.details(id);
            return IngredientEntryDetailsQueryResult.success(ingredientEntry);
        }
        catch (Exception e) {
            return IngredientEntryDetailsQueryResult.fail(e.getMessage());
        }
    }
    @PostMapping
    public CreateIngredientEntryCommandResult create(
            @RequestBody CreateIngredientEntryCommand createIngredientEntryCommand) {

        try {
            IngredientEntry ingredientEntry = ingredientEntryService.create(
                    createIngredientEntryCommand.getDirectory_id(),
                    createIngredientEntryCommand.getName(),
                    createIngredientEntryCommand.getDescription()
            );
            return CreateIngredientEntryCommandResult.success(ingredientEntry);
        }
        catch (Exception e) {
            return CreateIngredientEntryCommandResult.fail(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public DeleteIngredientEntryCommandResult delete(@PathVariable("id") String id) {
        try {
            boolean result = ingredientEntryService.delete(id);
            return DeleteIngredientEntryCommandResult.success(result);
        }
        catch (Exception e) {
            return DeleteIngredientEntryCommandResult.fail(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public RenameIngredientEntryCommandResult rename(@PathVariable("id") String id,
           @RequestBody RenameIngredientEntryCommand renameIngredientEntryCommand) {
        try {
            boolean result = ingredientEntryService.rename(id, renameIngredientEntryCommand.getNewName(),
                    renameIngredientEntryCommand.getNewDescription());
            return RenameIngredientEntryCommandResult.success(result);
        }
        catch (Exception e) {
            return RenameIngredientEntryCommandResult.fail(e.getMessage());
        }
    }
}
