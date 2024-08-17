package com.oneune.coffee.maker.services;

import com.oneune.coffee.maker.contracts.CRUDable;
import com.oneune.coffee.maker.dtos.MaterialDto;
import com.oneune.coffee.maker.dtos.RuleDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MaterialService implements CRUDable<MaterialDto> {

    @Override
    public MaterialDto post(MaterialDto materialDto) {
        return null;
    }

    @Override
    public MaterialDto getById(Long materialId) {
        return null;
    }

    @Override
    public List<MaterialDto> search(int page, int size) {
        return null;
    }

    @Override
    public MaterialDto put(Long materialId, MaterialDto materialDto) {
        return null;
    }

    @Override
    public MaterialDto deleteById(Long materialId) {
        return null;
    }

    public boolean validateByRule(RuleDto rule) {
        return false;
    }
}
