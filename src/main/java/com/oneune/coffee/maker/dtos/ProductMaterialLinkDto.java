package com.oneune.coffee.maker.dtos;

import com.oneune.coffee.maker.contracts.LinkEntityId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.javatuples.Pair;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class ProductMaterialLinkDto {
    LinkEntityId<Long, Long> id;
    RuleDto rule;
    MaterialDto material;
    Instant timestamp = Instant.now();
}
