package com.oneune.coffee.maker.dtos;

import com.oneune.coffee.maker.contracts.Identifiable;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public abstract class AbstractDto implements Identifiable {
    @Setter(AccessLevel.PRIVATE)
    Long id;
}
