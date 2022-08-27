package cloud.cholewa.tastee.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ingredient {
    private String name;
    private String quantity;
}
