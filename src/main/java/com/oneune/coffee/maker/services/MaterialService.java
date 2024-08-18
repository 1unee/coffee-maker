package com.oneune.coffee.maker.services;

import com.oneune.coffee.maker.contracts.CRUDable;
import com.oneune.coffee.maker.dtos.MaterialDto;
import com.oneune.coffee.maker.entities.MaterialEntity;
import com.oneune.coffee.maker.readers.MaterialReader;
import com.oneune.coffee.maker.repositories.MaterialRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MaterialService implements CRUDable<MaterialDto> {

    ModelMapper modelMapper;
    MaterialRepository materialRepository;
    MaterialReader materialReader;

    @Transactional
    @Override
    public MaterialDto post(MaterialDto materialDto) {
        MaterialEntity materialEntity = modelMapper.map(materialDto, MaterialEntity.class);
        materialRepository.saveAndFlush(materialEntity);
        return materialReader.get(materialEntity.getId());
    }

    @Override
    public MaterialDto getById(Long materialId) {
        return materialReader.get(materialId);
    }

    @Override
    public List<MaterialDto> search(int page, int size) {
        return materialReader.search(page, size);
    }

    @Transactional
    @Override
    public MaterialDto put(Long materialId, MaterialDto materialDto) {
        MaterialEntity materialEntity = materialReader.getEntity(materialId);
        modelMapper.map(materialDto, materialEntity);
        materialRepository.saveAndFlush(materialEntity);
        return materialReader.get(materialId);
    }

    @Transactional
    @Override
    public MaterialDto deleteById(Long materialId) {
        MaterialDto material = materialReader.get(materialId);
        materialRepository.deleteById(materialId);
        materialRepository.flush();
        return material;
    }
}
