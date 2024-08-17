package com.oneune.coffee.maker.entities;

import com.oneune.coffee.maker.contracts.Identifiable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@MappedSuperclass
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@ToString
public abstract class AbstractEntity implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    Long id;
}
