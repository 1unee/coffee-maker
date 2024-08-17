package com.oneune.coffee.maker.readers;

import com.google.gson.reflect.TypeToken;
import com.oneune.coffee.maker.dtos.MaterialDto;
import com.oneune.coffee.maker.entities.QMaterialEntity;
import com.oneune.coffee.maker.repositories.MaterialRepository;
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
public class MaterialReader {

    public final static Type MATERIAL_LIST_TYPE = TypeToken.getParameterized(List.class, MaterialDto.class).getType();
    private final static QMaterialEntity qMaterial = QMaterialEntity.materialEntity;

    MaterialRepository materialRepository;
    ModelMapper modelMapper;
    JPAQueryFactory queryFactory;
}
