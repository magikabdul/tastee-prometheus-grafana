package cloud.cholewa.tastee.api.model;

import cloud.cholewa.tastee.model.Ingredient;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
public class RecipeResponse {
    Long id;
    String title;
    String steps;
    Set<Ingredient> ingredients;
}
