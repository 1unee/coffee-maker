package com.oneune.coffee.maker.readers;

import com.google.gson.reflect.TypeToken;
import com.oneune.coffee.maker.dtos.MaterialDto;
import com.oneune.coffee.maker.dtos.ProductDto;
import com.oneune.coffee.maker.entities.ProductEntity;
import com.oneune.coffee.maker.entities.QMaterialEntity;
import com.oneune.coffee.maker.entities.QProductEntity;
import com.oneune.coffee.maker.repositories.MaterialRepository;
import com.oneune.coffee.maker.repositories.ProductRepository;
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
public class ProductReader {

    public final static Type PRODUCT_LIST_TYPE = TypeToken.getParameterized(List.class, ProductDto.class).getType();
    private final static QProductEntity qProduct = QProductEntity.productEntity;

    ProductRepository productRepository;
    ModelMapper modelMapper;
    JPAQueryFactory queryFactory;

    public ProductDto getByName(String productName) {
        ProductEntity productEntity = queryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq(productName))
                .fetchOne();
        return modelMapper.map(productEntity, ProductDto.class);
    }
}
