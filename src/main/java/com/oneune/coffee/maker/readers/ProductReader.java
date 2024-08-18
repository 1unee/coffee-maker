package com.oneune.coffee.maker.readers;

import com.google.gson.reflect.TypeToken;
import com.oneune.coffee.maker.dtos.ProductDto;
import com.oneune.coffee.maker.entities.ProductEntity;
import com.oneune.coffee.maker.entities.QProductEntity;
import com.oneune.coffee.maker.repositories.ProductRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

import static com.oneune.coffee.maker.readers.MaterialReader.qMaterialDefault;
import static com.oneune.coffee.maker.readers.RuleReader.qRuleDefault;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductReader {

    public final static Type PRODUCT_LIST_TYPE = TypeToken.getParameterized(List.class, ProductDto.class).getType();
    public final static QProductEntity qProductDefault = QProductEntity.productEntity;

    JPAQueryFactory queryFactory;
    ModelMapper modelMapper;
    ProductRepository productRepository;

    /**
     * Includes all joins.
     */
    private List<ProductEntity> multipleBaseQuery(BooleanExpression ... booleanExpressions) {
        return queryFactory.selectFrom(qProductDefault)
                .join(qRuleDefault).on(qProductDefault.id.eq(qRuleDefault.product.id))
                .join(qMaterialDefault).on(qRuleDefault.material.id.eq(qMaterialDefault.id))
                .where(booleanExpressions)
                .fetch();
    }

    private ProductEntity singleBaseQuery(BooleanExpression ... booleanExpressions) {
        List<ProductEntity> productEntities = multipleBaseQuery(booleanExpressions);
        if (productEntities.size() != 1) {
            throw new IllegalStateException("Found %s product entities instead one".formatted(productEntities.size()));
        }
        return productEntities.get(0);
    }

    public ProductDto getByName(String productName) {
        ProductEntity productEntity = singleBaseQuery(qProductDefault.name.eq(productName));
        return modelMapper.map(productEntity, ProductDto.class);
    }

    public ProductEntity getEntity(Long productId) {
        return singleBaseQuery(qProductDefault.id.eq(productId));
    }

    public ProductDto get(Long productId) {
        ProductEntity productEntity = getEntity(productId);
        return modelMapper.map(productEntity, ProductDto.class);
    }

    public List<ProductDto> search(int page, int size) {
        Page<ProductEntity> pageOfProducts = productRepository.findAll(PageRequest.of(page, size));
        return modelMapper.map(pageOfProducts.getContent(), PRODUCT_LIST_TYPE);
    }
}
