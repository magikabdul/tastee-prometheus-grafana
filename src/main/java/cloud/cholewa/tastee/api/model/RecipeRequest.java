package cloud.cholewa.tastee.api.model;

import cloud.cholewa.tastee.model.Ingredient;

import java.util.Set;

public record RecipeRequest(
        String title,
        String steps,
        Set<Ingredient> ingredients) {
}
