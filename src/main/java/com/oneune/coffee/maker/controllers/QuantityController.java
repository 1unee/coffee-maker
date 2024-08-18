package com.oneune.coffee.maker.controllers;

import com.oneune.coffee.maker.contracts.CRUDable;
import com.oneune.coffee.maker.dtos.QuantityDto;
import com.oneune.coffee.maker.services.QuantityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quantities")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class QuantityController implements CRUDable<QuantityDto> {

    QuantityService quantityService;

    @PostMapping
    @Override
    public QuantityDto post(@RequestBody QuantityDto quantity) {
        return quantityService.post(quantity);
    }

    @GetMapping("{quantity-id}")
    @Override
    public QuantityDto getById(@PathVariable(name = "quantity-id") Long quantityId) {
        return quantityService.getById(quantityId);
    }

    @GetMapping("search")
    @Override
    public List<QuantityDto> search(@RequestParam int page, @RequestParam int size) {
        return quantityService.search(page, size);
    }

    @PutMapping("{quantity-id}")
    @Override
    public QuantityDto put(@PathVariable(name = "quantity-id") Long quantityId, QuantityDto quantity) {
        return quantityService.put(quantityId, quantity);
    }

    @DeleteMapping("{quantity-id}")
    @Override
    public QuantityDto deleteById(@PathVariable(name = "quantity-id") Long quantityId) {
        return quantityService.deleteById(quantityId);
    }
}
