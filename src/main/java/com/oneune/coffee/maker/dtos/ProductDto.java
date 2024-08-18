package com.oneune.coffee.maker.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

// todo: оставить только сборку с помощью билдера
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
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
