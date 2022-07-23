package by.teachmeskills.eshop.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@SuperBuilder
public class Category extends BaseEntity {
    private String name;
    private String imagePath;
    private List<Product> productList;
}
