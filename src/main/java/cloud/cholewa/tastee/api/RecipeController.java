package cloud.cholewa.tastee.api;

import cloud.cholewa.tastee.api.mapper.RecipeControllerMapper;
import cloud.cholewa.tastee.api.model.RecipeRequest;
import cloud.cholewa.tastee.api.model.RecipeResponse;
import cloud.cholewa.tastee.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@RestController
@RequestMapping("recipes")
@Slf4j
class RecipeController {
    private final RecipeService recipeService;
    private final RecipeControllerMapper recipeControllerMapper;

    @GetMapping("{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(
                recipeControllerMapper.toRecipeResponse(
                        recipeService.getRecipeById(id)
                )
        );
    }

    @SneakyThrows
    @GetMapping
    public ResponseEntity<Page<RecipeResponse>> getAllRecipes(
            Pageable pageable,
            @RequestParam(defaultValue = "false") Boolean slow
    ) {
        if (slow) {
            int sleepTime = new SecureRandom().nextInt(1000);
            if (sleepTime > 750) {
                log.warn("I'm tired. Will answer in {} ms", sleepTime);
            } else {
                log.info("Will answer in {} ms", sleepTime);
            }
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        }

        Page<RecipeResponse> responses = recipeService.getAllRecipes(pageable)
                .map(recipe -> RecipeResponse.builder()
                        .title(recipe.getTitle())
                        .steps(recipe.getSteps())
                        .ingredients(recipe.getIngredients())
                        .build());

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<Void> addRecipe(@RequestBody RecipeRequest recipeRequest) {
        return ResponseEntity.created(
                URI.create("/" + recipeService.addRecipe(recipeRequest))
        ).build();
    }
}
