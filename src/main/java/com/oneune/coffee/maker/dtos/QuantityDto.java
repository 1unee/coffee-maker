package com.oneune.coffee.maker.dtos;

import com.oneune.coffee.maker.enums.MeasureType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Data
public class QuantityDto extends AbstractDto {
    MaterialDto material;
    MeasureType measureType;
    BigDecimal multiplier;
    BigDecimal amount;
}
