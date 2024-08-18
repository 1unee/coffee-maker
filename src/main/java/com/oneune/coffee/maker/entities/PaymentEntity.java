package com.oneune.coffee.maker.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payment")
@SequenceGenerator(sequenceName = "payment_id_seq", name = "id_seq", allocationSize = 1)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class PaymentEntity extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

    BigDecimal total;
    Instant timestamp;
    Boolean success;
    String message;
}
