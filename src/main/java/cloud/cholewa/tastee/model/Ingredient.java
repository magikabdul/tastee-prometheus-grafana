package cloud.cholewa.tastee.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Ingredient {
    private String name;
    private String quantity;
}
