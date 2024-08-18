package com.oneune.coffee.maker.readers;

import com.google.gson.reflect.TypeToken;
import com.oneune.coffee.maker.dtos.RuleDto;
import com.oneune.coffee.maker.entities.QRuleEntity;
import com.oneune.coffee.maker.repositories.RuleRepository;
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
public class RuleReader {

    public final static Type RULE_LIST_TYPE = TypeToken.getParameterized(List.class, RuleDto.class).getType();
    public final static QRuleEntity qRuleDefault = QRuleEntity.ruleEntity;

    JPAQueryFactory queryFactory;
    ModelMapper modelMapper;
    RuleRepository ruleRepository;

}
