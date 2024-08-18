package com.oneune.coffee.maker.readers;

import com.google.gson.reflect.TypeToken;
import com.oneune.coffee.maker.dtos.QuantityDto;
import com.oneune.coffee.maker.entities.QQuantityEntity;
import com.oneune.coffee.maker.entities.QuantityEntity;
import com.oneune.coffee.maker.repositories.QuantityRepository;
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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.oneune.coffee.maker.readers.MaterialReader.qMaterialDefault;
import static java.util.stream.Collectors.toMap;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class QuantityReader {

    public final static Type QUANTITY_LIST_TYPE = TypeToken.getParameterized(List.class, QuantityDto.class).getType();
    public final static QQuantityEntity qQuantityDefault = QQuantityEntity.quantityEntity;

    ModelMapper modelMapper;
    JPAQueryFactory queryFactory;
    QuantityRepository quantityRepository;

    /**
     * Includes all joins.
     */
    private List<QuantityEntity> multipleBaseQuery(BooleanExpression... booleanExpressions) {
        return queryFactory.selectFrom(qQuantityDefault)
                .join(qMaterialDefault).on(qQuantityDefault.material.id.eq(qMaterialDefault.id))
                .where(booleanExpressions)
                .fetch();
    }

    private QuantityEntity singleBaseQuery(BooleanExpression ... booleanExpressions) {
        List<QuantityEntity> quantityEntities = multipleBaseQuery(booleanExpressions);
        if (quantityEntities.size() != 1) {
            throw new IllegalStateException("Found %s quantity entities instead one".formatted(quantityEntities.size()));
        }
        return quantityEntities.get(0);
    }

    public QuantityEntity getEntity(Long quantityId) {
        return singleBaseQuery(qQuantityDefault.id.eq(quantityId));
    }

    public QuantityDto get(Long quantityId) {
        QuantityEntity quantityEntity = getEntity(quantityId);
        return modelMapper.map(quantityEntity, QuantityDto.class);
    }

    public List<QuantityDto> search(int page, int size) {
        Page<QuantityEntity> pageOfQuantities = quantityRepository.findAll(PageRequest.of(page, size));
        return modelMapper.map(pageOfQuantities.getContent(), QUANTITY_LIST_TYPE);
    }

    public List<QuantityDto> getQuantities(Collection<Long> materialIds) {
        List<QuantityEntity> quantityEntities = multipleBaseQuery(qQuantityDefault.material.id.in(materialIds));
        return modelMapper.map(quantityEntities, QUANTITY_LIST_TYPE);
    }

    public Map<Long, QuantityDto> getQuantitiesMap(Collection<Long> materialIds) {
        return getQuantities(materialIds).stream()
                .collect(toMap(quantity -> quantity.getMaterial().getId(), Function.identity(), (l, r) -> l));
    }

    public Map<Long, QuantityEntity> getQuantityEntitiesMap(Collection<Long> materialIds) {
        return multipleBaseQuery(qQuantityDefault.material.id.in(materialIds)).stream()
                .collect(toMap(quantity -> quantity.getMaterial().getId(), Function.identity(), (l, r) -> l));
    }
}
