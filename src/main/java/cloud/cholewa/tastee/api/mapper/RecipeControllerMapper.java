package cloud.cholewa.tastee.api.mapper;

import cloud.cholewa.tastee.api.model.RecipeResponse;
import cloud.cholewa.tastee.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipeControllerMapper {

    @Mapping(target = "id", ignore = true)
    RecipeResponse toRecipeResponse(Recipe recipe);
}
