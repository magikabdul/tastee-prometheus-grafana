package cloud.cholewa.tastee.mapper;

import cloud.cholewa.tastee.db.model.IngredientEntity;
import cloud.cholewa.tastee.db.model.RecipeEntity;
import cloud.cholewa.tastee.model.Ingredient;
import cloud.cholewa.tastee.model.Recipe;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    Recipe toRecipe(RecipeEntity recipeEntity);

    Ingredient toIngredient(IngredientEntity ingredientEntity);

    default Page<Recipe> toRecipe(Page<RecipeEntity> recipeEntities) {
        return recipeEntities.map(recipeEntity -> Recipe.builder()
                .title(recipeEntity.getTitle())
                .steps(recipeEntity.getSteps())
                .ingredients(
                        recipeEntity.getIngredients()
                                .stream()
                                .map(this::toIngredient)
                                .collect(Collectors.toSet())
                )
                .build());
    }
}
