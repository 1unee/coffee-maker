package com.oneune.coffee.maker.contracts;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PRIVATE)
@Data
public class LinkEntityId<L, R> implements Serializable {
    L leftPartId;
    R rightPartId;
}
