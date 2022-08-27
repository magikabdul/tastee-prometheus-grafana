package cloud.cholewa.tastee.service;

import cloud.cholewa.tastee.api.model.RecipeRequest;
import cloud.cholewa.tastee.db.model.IngredientEntity;
import cloud.cholewa.tastee.db.model.RecipeEntity;
import cloud.cholewa.tastee.db.repository.RecipeRepository;
import cloud.cholewa.tastee.mapper.RecipeMapper;
import cloud.cholewa.tastee.model.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public Long addRecipe(RecipeRequest recipeRequest) {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setTitle(recipeRequest.title());
        recipeEntity.setSteps(recipeRequest.steps());
        recipeEntity.setIngredients(recipeRequest.ingredients()
                .stream()
                .map(ingredient -> {
                    IngredientEntity ingredientEntity = new IngredientEntity();
                    ingredientEntity.setName(ingredient.getName());
                    ingredientEntity.setQuantity(ingredient.getQuantity());
                    return ingredientEntity;
                })
                .collect(Collectors.toSet()));

        return recipeRepository.save(recipeEntity).getId();
    }

    public Recipe getRecipeById(Long id) {
        return recipeMapper.toRecipe(recipeRepository.findById(id).orElseThrow());
    }

    public Page<Recipe> getAllRecipes(Pageable pageable) {
        return recipeMapper.toRecipe(recipeRepository.findAll(pageable));
    }
}
