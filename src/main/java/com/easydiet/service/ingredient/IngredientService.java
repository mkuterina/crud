package com.easydiet.service.ingredient;

import com.easydiet.domain.ingredient.Ingredient;
import com.easydiet.domain.ingredient.IngredientName;
import com.easydiet.domain.ingredient.IngredientOperations;
import com.easydiet.domain.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient create(String name) {
        return ingredientRepository.save(
                IngredientOperations.create(
                        IngredientName.create(name)
                )
        );
    }

    public boolean rename(String id, String newName) throws IngredientNotFoundException {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);

        if (optionalIngredient.isEmpty()) {
            throw new IngredientNotFoundException(id);
        }
        else {
            Ingredient ingredient = optionalIngredient.get();
            boolean result = ingredient.rename(
                    IngredientName.create(newName)
            );
            ingredientRepository.save(ingredient);
            return result;
        }
    }

    public boolean delete(String id) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);

        if (optionalIngredient.isEmpty()) {
            return false;
        }
        else {
            Ingredient ingredient = optionalIngredient.get();
            boolean result = ingredient.delete();
            ingredientRepository.save(ingredient);
            return result;
        }
    }

    public List<Ingredient> list() {
     return ingredientRepository.findAll().stream()
             .filter(ingredient -> !ingredient.isDeleted())
             .toList();
    }

    public Ingredient details(String id) throws IngredientNotFoundException {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        if (optionalIngredient.isEmpty()) {
            throw new IngredientNotFoundException(id);
        }
        else {
            Ingredient ingredient = optionalIngredient.get();
            return ingredient;
        }
    }
}
