package com.oneune.coffee.maker.dtos;

import com.oneune.coffee.maker.enums.MeasureType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class MaterialDto extends AbstractDto {
    String name;
    MeasureType measureType;
    BigDecimal multiplierRelativeMeasureType;
    BigDecimal amount;
}
