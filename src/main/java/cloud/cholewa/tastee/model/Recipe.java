package cloud.cholewa.tastee.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public
class Recipe {
    private String title;
    private String steps;
    private Set<Ingredient> ingredients;
}
