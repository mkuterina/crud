package com.easydiet.service.ingredient_entry;

import com.easydiet.domain.directory.DirectoryId;
import com.easydiet.domain.ingredient_entry.*;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientEntryService {
    private final IngredientEntryRepository ingredientEntryRepository;

    @Autowired
    public IngredientEntryService(IngredientEntryRepository ingredientEntryRepository) {
        this.ingredientEntryRepository = ingredientEntryRepository;
    }

    @NotNull
    public IngredientEntry create(String directoryId, String name, String description) {
        return ingredientEntryRepository.saveAndFlush(
                IngredientEntry.create(
                        DirectoryId.create(directoryId),
                        IngredientEntryName.create(name),
                        IngredientEntryDescription.create(description)
                )
        );
    }

    public boolean rename(String id, String newName, String newDescription)
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
            ingredientEntryRepository.save(ingredientEntry);
            return result;
        }
    }

    public boolean delete(String id) {
        Optional<IngredientEntry> optionalIngredientEntry = ingredientEntryRepository.findByIdentifier(id);

        if (optionalIngredientEntry.isEmpty()) {
            return false;
        } else {
            IngredientEntry ingredientEntry = optionalIngredientEntry.get();
            boolean result = ingredientEntry.delete();
            ingredientEntryRepository.save(ingredientEntry);
            return result;
        }
    }

    public List<IngredientEntry> list() {
        return ingredientEntryRepository.listAll().stream()
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

    public List<IngredientEntry> list(String directoryId) {
        return ingredientEntryRepository.listAllByDirectoryId(directoryId).stream()
                .filter(IngredientEntry -> !IngredientEntry.isDeleted())
                .toList();
    }
}

