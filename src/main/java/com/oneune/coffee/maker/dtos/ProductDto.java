package com.oneune.coffee.maker.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Data
public class ProductDto extends AbstractDto {

    String name;
    BigDecimal price;
    List<RuleDto> rules;

    /**
     * For query DSL (getTheMostPopular method)
     */
    public ProductDto(Long id, String name, BigDecimal price) {
        super(id);
        this.name = name;
        this.price = price;
    }
}
