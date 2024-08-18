package com.oneune.coffee.maker.entities;

import com.oneune.coffee.maker.contracts.Identifiable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * Abstract class contains Long ID field.
 */
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
@ToString
public abstract class AbstractEntity implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    Long id;
}
