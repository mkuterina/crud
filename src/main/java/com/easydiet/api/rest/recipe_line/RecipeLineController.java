package com.easydiet.api.rest.recipe_line;

import com.easydiet.domain.recipe_line.RecipeLine;
import com.easydiet.service.recipe_line.RecipeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recipe_line")
public class RecipeLineController {

    private final RecipeLineService recipeLineService;

    @Autowired
    public RecipeLineController(RecipeLineService recipeLineService) {
        this.recipeLineService = recipeLineService;
    }

    @GetMapping("deprecated")
    public ListRecipeLinesQueryResult list() {
        try {
            List<RecipeLine> recipeLines = recipeLineService.list();
            return ListRecipeLinesQueryResult.success(recipeLines);
        } catch (Exception e) {
            return ListRecipeLinesQueryResult.fail(e.getMessage());
        }
    }

    @GetMapping
    public ListRecipeLinesQueryResult list(
            @RequestParam(value = "recipe_entry_id", required = false) String recipeEntryId) {
        try {
            if (recipeEntryId == null || recipeEntryId.isBlank()) {
                List<RecipeLine> recipeLines = recipeLineService.list();
                return ListRecipeLinesQueryResult.success(recipeLines);
            }
            else {
                List<RecipeLine> recipeLines = recipeLineService.list(recipeEntryId);
                return ListRecipeLinesQueryResult.success(recipeLines);
            }
        } catch (Exception e) {
            return ListRecipeLinesQueryResult.fail(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public RecipeLineDetailsQueryResult details(@PathVariable("id") String id) {
        try {
            RecipeLine recipeLine = recipeLineService.details(id);
            return RecipeLineDetailsQueryResult.success(recipeLine);
        } catch (Exception e) {
            return RecipeLineDetailsQueryResult.fail(e.getMessage());
        }
    }

    @PostMapping
    public CreateRecipeLineCommandResult create(@RequestBody CreateRecipeLineCommand createRecipeLineCommand) {
        try {
            RecipeLine recipeLine = recipeLineService.create(createRecipeLineCommand.getRecipe_entry_id(),
                    createRecipeLineCommand.getDirectory_id(),
                    createRecipeLineCommand.getType());
            return CreateRecipeLineCommandResult.success(recipeLine);
        } catch (Exception e) {
            return CreateRecipeLineCommandResult.fail(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public DeleteRecipeLineCommandResult delete(@PathVariable("id") String id) {
        try {
            boolean result = recipeLineService.delete(id);
            return DeleteRecipeLineCommandResult.success(result);
        }
        catch (Exception e) {
            return DeleteRecipeLineCommandResult.fail(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public RetypeRecipeLineCommandResult retype(@PathVariable("id") String id,
              @RequestBody RetypeRecipeLineCommand retypeRecipeLineCommand) {
        try {
            boolean result = recipeLineService.retype(id, retypeRecipeLineCommand.getNewType());
            return RetypeRecipeLineCommandResult.success(result);
        }
        catch (Exception e) {
            return RetypeRecipeLineCommandResult.fail(e.getMessage());
        }
    }
}


