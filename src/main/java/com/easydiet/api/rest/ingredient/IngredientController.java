package com.easydiet.api.rest.ingredient;

import com.easydiet.domain.ingredient.Ingredient;
import com.easydiet.service.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ListIngredientsQueryResult list() {
        try {
            List<Ingredient> ingredients = ingredientService.list();
            return ListIngredientsQueryResult.success(ingredients);
        }
        catch (Exception e) {
            return ListIngredientsQueryResult.fail(e.getMessage());
        }
    }
    @GetMapping("{id}")
    public IngredientDetailsQueryResult details(@PathVariable("id") String id) {
        try {
            Ingredient ingredient = ingredientService.details(id);
            return IngredientDetailsQueryResult.success(ingredient);
        }
        catch (Exception e) {
            return IngredientDetailsQueryResult.fail(e.getMessage());
        }
    }

    @PostMapping
    public CreateIngredientCommandResult create(@RequestBody CreateIngredientCommand createIngredientCommand) {
        try {
            Ingredient ingredient = ingredientService.create(createIngredientCommand.getName());
            return CreateIngredientCommandResult.success(ingredient);
        }
        catch (Exception e) {
            return CreateIngredientCommandResult.fail(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public DeleteIngredientCommandResult delete(@PathVariable("id") String id) {
        try {
            boolean result = ingredientService.delete(id);
            return DeleteIngredientCommandResult.success(result);
        }
        catch (Exception e) {
            return DeleteIngredientCommandResult.fail(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public RenameIngredientCommandResult rename(@PathVariable("id") String id, @RequestBody RenameIngredientCommand renameIngredientCommand) {
        try {
            boolean result = ingredientService.rename(id, renameIngredientCommand.getNewName());
            return RenameIngredientCommandResult.success(result);
        }
        catch (Exception e) {
            return RenameIngredientCommandResult.fail(e.getMessage());
        }
    }
}
