package cloud.cholewa.tastee.db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "recipes")
@NoArgsConstructor
@Getter
@Setter
class RecipeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String steps;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "recipe_id")
    private Set<IngredientEntity> ingredients;
}
