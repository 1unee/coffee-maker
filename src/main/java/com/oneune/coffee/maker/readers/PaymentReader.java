package com.oneune.coffee.maker.readers;

import com.google.gson.reflect.TypeToken;
import com.oneune.coffee.maker.dtos.PaymentDto;
import com.oneune.coffee.maker.dtos.ProductDto;
import com.oneune.coffee.maker.entities.QPaymentEntity;
import com.oneune.coffee.maker.repositories.ProductRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PaymentReader {

    public final static Type PAYMENT_LIST_TYPE = TypeToken.getParameterized(List.class, PaymentDto.class).getType();
    public final static QPaymentEntity qPaymentDefault = QPaymentEntity.paymentEntity;

    JPAQueryFactory queryFactory;
    ModelMapper modelMapper;
    ProductRepository productRepository;

    public ProductDto getTheMostPopular() {
        return queryFactory
                .select(Projections.constructor(
                        ProductDto.class,
                        qPaymentDefault.product.id,
                        qPaymentDefault.product.name,
                        qPaymentDefault.product.price
                ))
                .from(qPaymentDefault)
                .groupBy(
                        qPaymentDefault.product.id,
                        qPaymentDefault.product.name,
                        qPaymentDefault.product.price
                )
                .orderBy(
                        qPaymentDefault.product.id.count().desc()
                )
                .fetchFirst();
    }
}
