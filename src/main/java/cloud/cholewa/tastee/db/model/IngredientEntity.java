package cloud.cholewa.tastee.db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
@NoArgsConstructor
@Getter
@Setter
public class IngredientEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String quantity;
}
