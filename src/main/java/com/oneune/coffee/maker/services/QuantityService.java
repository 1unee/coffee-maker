package com.oneune.coffee.maker.services;

import com.oneune.coffee.maker.contracts.CRUDable;
import com.oneune.coffee.maker.contracts.Identifiable;
import com.oneune.coffee.maker.dtos.QuantityDto;
import com.oneune.coffee.maker.dtos.RuleDto;
import com.oneune.coffee.maker.entities.QuantityEntity;
import com.oneune.coffee.maker.readers.QuantityReader;
import com.oneune.coffee.maker.repositories.QuantityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class QuantityService implements CRUDable<QuantityDto> {

    ModelMapper modelMapper;
    QuantityRepository quantityRepository;
    QuantityReader quantityReader;

    @Transactional
    @Override
    public QuantityDto post(QuantityDto quantityDto) {
        QuantityEntity quantityEntity = modelMapper.map(quantityDto, QuantityEntity.class);
        quantityRepository.saveAndFlush(quantityEntity);
        return quantityReader.get(quantityEntity.getId());
    }

    @Override
    public QuantityDto getById(Long quantityId) {
        return quantityReader.get(quantityId);
    }

    @Override
    public List<QuantityDto> search(int page, int size) {
        return quantityReader.search(page, size);
    }

    @Transactional
    @Override
    public QuantityDto put(Long quantityId, QuantityDto quantityDto) {
        QuantityEntity quantityEntity = quantityReader.getEntity(quantityId);
        modelMapper.map(quantityDto, quantityEntity);
        quantityRepository.saveAndFlush(quantityEntity);
        return quantityReader.get(quantityId);
    }

    @Transactional
    @Override
    public QuantityDto deleteById(Long quantityId) {
        QuantityDto quantity = quantityReader.get(quantityId);
        quantityRepository.deleteById(quantityId);
        quantityRepository.flush();
        return quantity;
    }

    public boolean validate(List<RuleDto> rules) {
        List<Long> materialIds = rules.stream().map(RuleDto::getMaterial).map(Identifiable::getId).toList();
        Map<Long, QuantityDto> quantitiesMap = quantityReader.getQuantitiesMap(materialIds);

        return rules.stream()
                .map(rule -> Pair.of(rule, quantitiesMap.get(rule.getMaterial().getId())))
                .map(pair -> Pair.of(
                        pair.getFirst().getMultiplier().multiply(pair.getFirst().getAmount()),
                        pair.getSecond().getMultiplier().multiply(pair.getSecond().getAmount())
                ))
                .allMatch(pair -> pair.getFirst().compareTo(pair.getSecond()) < 0);
    }

    @Transactional
    public void spend(List<RuleDto> rules) {
        List<Long> materialIds = rules.stream().map(RuleDto::getMaterial).map(Identifiable::getId).toList();
        Map<Long, QuantityEntity> quantityEntitiesMap = quantityReader.getQuantityEntitiesMap(materialIds);
        rules.forEach(ruleEntity -> {
            QuantityEntity quantityEntity = quantityEntitiesMap.get(ruleEntity.getMaterial().getId());
            BigDecimal actualAmount = quantityEntity.getAmount().multiply(quantityEntity.getMultiplier());
            BigDecimal amountToSubstract = ruleEntity.getAmount().multiply(ruleEntity.getMultiplier());
            quantityEntity.setAmount(actualAmount.subtract(amountToSubstract));
        });
        quantityRepository.saveAllAndFlush(quantityEntitiesMap.values().stream().toList());
    }
}
