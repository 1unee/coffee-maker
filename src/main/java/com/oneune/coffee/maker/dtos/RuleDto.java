package com.oneune.coffee.maker.dtos;

import com.oneune.coffee.maker.entities.RuleMaterialLinkEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class RuleDto extends AbstractDto {

    String name;
    String description;
    List<RuleMaterialLinkEntity> productMaterialLinks = new LinkedList<>();
}
