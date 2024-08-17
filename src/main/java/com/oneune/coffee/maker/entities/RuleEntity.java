package com.oneune.coffee.maker.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Entity
@Table(name = "rule")
@SequenceGenerator(sequenceName = "product_id_seq", name = "id_seq", allocationSize = 1)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class RuleEntity extends AbstractEntity {

    String name;
    String description;

    @OneToOne
    ProductEntity product;

    @OneToMany(mappedBy = "rule", cascade = CascadeType.ALL)
    @ToString.Exclude
    List<RuleMaterialLinkEntity> ruleMaterialLinks = new ArrayList<>();
}
