package com.oneune.coffee.maker.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Data
public class PaymentDto extends AbstractDto {
    ProductDto product;
    BigDecimal total;
    Instant timestamp;
    Boolean success;
    String message;
}
