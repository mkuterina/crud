package com.easydiet.api.rest.ingredient;

import com.easydiet.domain.ingredient.Ingredient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngredientDetailsQueryResult {

    private String status;
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deleteDate;
    private String name;
    private String message;

    public IngredientDetailsQueryResult(String status, Ingredient ingredient, String message) {
        this.status = status;
        this.message = message;
        if (ingredient != null) {
            this.id = ingredient.getId();
            this.createDate = ingredient.getCreateDate();
            this.deleteDate = ingredient.getDeleteDate();
            this.name = ingredient.getName();
        }
    }

    public static IngredientDetailsQueryResult success(Ingredient ingredient) {
        return new IngredientDetailsQueryResult("success", ingredient, null);
    }

    public static IngredientDetailsQueryResult fail(String message) {
        return new IngredientDetailsQueryResult("fail", null, message);
    }
}
