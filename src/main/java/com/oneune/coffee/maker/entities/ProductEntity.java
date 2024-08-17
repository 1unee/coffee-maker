package com.oneune.coffee.maker.entities;

import com.oneune.coffee.maker.dtos.MaterialDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Entity
@Table(name = "product")
@SequenceGenerator(sequenceName = "product_id_seq", name = "id_seq", allocationSize = 1)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class ProductEntity extends AbstractEntity {

    String name;

    @OneToOne(mappedBy = "product")
    RuleEntity rule;
}
