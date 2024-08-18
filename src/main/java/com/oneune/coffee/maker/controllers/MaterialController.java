package com.oneune.coffee.maker.controllers;

import com.oneune.coffee.maker.aop.annotations.Security;
import com.oneune.coffee.maker.contracts.CRUDable;
import com.oneune.coffee.maker.dtos.MaterialDto;
import com.oneune.coffee.maker.services.MaterialService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("materials")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MaterialController implements CRUDable<MaterialDto> {

    MaterialService materialService;

    @PostMapping
    @Security
    @Override
    public MaterialDto post(@RequestBody MaterialDto material) {
        return materialService.post(material);
    }

    @GetMapping("{material-id}")
    @Override
    public MaterialDto getById(@PathVariable(name = "material-id") Long materialId) {
        return materialService.getById(materialId);
    }

    @GetMapping("search")
    @Override
    public List<MaterialDto> search(@RequestParam int page, @RequestParam int size) {
        return materialService.search(page, size);
    }

    @PutMapping("{material-id}")
    @Security
    @Override
    public MaterialDto put(@PathVariable(name = "material-id") Long materialId, @RequestBody MaterialDto material) {
        return materialService.put(materialId, material);
    }

    @DeleteMapping("{material-id}")
    @Security
    @Override
    public MaterialDto deleteById(@PathVariable(name = "material-id") Long materialId) {
        return materialService.deleteById(materialId);
    }
}
