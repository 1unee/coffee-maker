package com.oneune.coffee.maker.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Product class
 */
@Entity
@Table(name = "product")
@SequenceGenerator(sequenceName = "product_id_seq", name = "id_seq", allocationSize = 1)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class ProductEntity extends AbstractEntity {

    String name;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<RuleEntity> rules = new ArrayList<>();
}
