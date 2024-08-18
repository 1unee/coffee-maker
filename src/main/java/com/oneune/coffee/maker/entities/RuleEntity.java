package com.oneune.coffee.maker.entities;

import com.oneune.coffee.maker.enums.MeasureType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * Rule class
 */
@Entity
@Table(name = "rule")
@SequenceGenerator(sequenceName = "rule_id_seq", name = "id_seq", allocationSize = 1)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class RuleEntity extends AbstractEntity {

    @ManyToOne
    @ToString.Exclude
    ProductEntity product;

    @OneToOne
    @JoinColumn(name = "material_id")
    MaterialEntity material;

    @Enumerated(EnumType.STRING)
    MeasureType measureType;
    BigDecimal multiplier;
    BigDecimal amount;
}
