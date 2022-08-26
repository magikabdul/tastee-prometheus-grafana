package cloud.cholewa.tastee.api;

import cloud.cholewa.tastee.api.model.RecipeRequest;
import cloud.cholewa.tastee.api.model.RecipeResponse;
import cloud.cholewa.tastee.mapper.RecipeResponseMapper;
import cloud.cholewa.tastee.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("recipes")
@Slf4j
class RecipesController {
    private final RecipeService recipeService;
    private final RecipeResponseMapper recipeResponseMapper;

    @GetMapping("{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(
                recipeResponseMapper.mapToRecipeResponse(
                        recipeService.getRecipeById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<Void> addRecipe(@RequestBody RecipeRequest recipeRequest) {
        return ResponseEntity.created(URI.create("/" + recipeService.addRecipe(recipeRequest))).build();
    }
}
