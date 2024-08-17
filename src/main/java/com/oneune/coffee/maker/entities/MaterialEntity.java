package com.oneune.coffee.maker.entities;

import com.oneune.coffee.maker.enums.MeasureType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "material")
@SequenceGenerator(sequenceName = "material_id_seq", name = "id_seq", allocationSize = 1)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class MaterialEntity extends AbstractEntity {
    String name;
    @Enumerated(EnumType.STRING)
    MeasureType measureType;
    BigDecimal multiplierRelativeMeasureType;
    BigDecimal amount;
}
