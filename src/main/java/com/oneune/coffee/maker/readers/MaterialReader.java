package com.oneune.coffee.maker.readers;

import com.google.gson.reflect.TypeToken;
import com.oneune.coffee.maker.dtos.MaterialDto;
import com.oneune.coffee.maker.entities.MaterialEntity;
import com.oneune.coffee.maker.entities.QMaterialEntity;
import com.oneune.coffee.maker.repositories.MaterialRepository;
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

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MaterialReader {

    public final static Type MATERIAL_LIST_TYPE = TypeToken.getParameterized(List.class, MaterialDto.class).getType();
    public final static QMaterialEntity qMaterialDefault = QMaterialEntity.materialEntity;

    JPAQueryFactory queryFactory;
    ModelMapper modelMapper;
    MaterialRepository materialRepository;

    /**
     * Includes all joins.
     */
    private List<MaterialEntity> multipleBaseQuery(BooleanExpression... booleanExpressions) {
        return queryFactory.selectFrom(qMaterialDefault)
                .where(booleanExpressions)
                .fetch();
    }

    private MaterialEntity singleBaseQuery(BooleanExpression ... booleanExpressions) {
        List<MaterialEntity> materialEntities = multipleBaseQuery(booleanExpressions);
        if (materialEntities.size() != 1) {
            throw new IllegalStateException("Found %s product entities instead one".formatted(materialEntities.size()));
        }
        return materialEntities.get(0);
    }

    public MaterialEntity getEntity(Long materialId) {
        return singleBaseQuery(qMaterialDefault.id.eq(materialId));
    }

    public MaterialDto get(Long materialId) {
        MaterialEntity materialEntity = getEntity(materialId);
        return modelMapper.map(materialEntity, MaterialDto.class);
    }

    public List<MaterialDto> search(int page, int size) {
        Page<MaterialEntity> pageOfMaterials = materialRepository.findAll(PageRequest.of(page, size));
        return modelMapper.map(pageOfMaterials.getContent(), MATERIAL_LIST_TYPE);
    }
}
