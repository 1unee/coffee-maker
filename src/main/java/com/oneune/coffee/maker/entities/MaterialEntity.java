package com.oneune.coffee.maker.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * Material class
 */
@Entity
@Table(name = "material")
@SequenceGenerator(sequenceName = "material_id_seq", name = "id_seq", allocationSize = 1)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Data
public class MaterialEntity extends AbstractEntity {
    String name;
}
