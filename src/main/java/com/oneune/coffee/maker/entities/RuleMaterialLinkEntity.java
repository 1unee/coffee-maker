package com.oneune.coffee.maker.entities;

import com.oneune.coffee.maker.contracts.LinkEntityId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Entity
@Table(name = "rule_material_link")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class RuleMaterialLinkEntity {

    @EmbeddedId
    LinkEntityId<Long, Long> id;

    @ManyToOne
    @MapsId("leftPartId")
    @JoinColumn(name = "product_id")
    RuleEntity rule;

    @ManyToOne
    @MapsId("rightPartId")
    @JoinColumn(name = "material_id")
    MaterialEntity material;

    Instant timestamp = Instant.now();
}
