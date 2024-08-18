package com.oneune.coffee.maker.entities;

import com.oneune.coffee.maker.enums.MeasureType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "quantity")
@SequenceGenerator(sequenceName = "quantity_id_seq", name = "id_seq", allocationSize = 1)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class QuantityEntity extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "material_id")
    MaterialEntity material;

    @Enumerated(EnumType.STRING)
    MeasureType measureType;
    BigDecimal multiplier;
    BigDecimal amount;
}
