package com.oneune.coffee.maker.controllers;

import com.oneune.coffee.maker.aop.annotations.Security;
import com.oneune.coffee.maker.contracts.CRUDable;
import com.oneune.coffee.maker.dtos.ProductDto;
import com.oneune.coffee.maker.dtos.RuleDto;
import com.oneune.coffee.maker.services.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductController implements CRUDable<ProductDto> {

    ProductService productService;

    @PostMapping
    @Security
    @Override
    public ProductDto post(@RequestBody ProductDto product) {
        return productService.post(product);
    }

    @GetMapping("{product-id}")
    @Override
    public ProductDto getById(@PathVariable(name = "product-id") Long productId) {
        return productService.getById(productId);
    }

    @GetMapping("search")
    @Override
    public List<ProductDto> search(@RequestParam int page, @RequestParam int size) {
        return productService.search(page, size);
    }

    @PutMapping("{product-id}")
    @Security
    @Override
    public ProductDto put(@PathVariable(name = "product-id") Long productId, @RequestBody ProductDto product) {
        return productService.put(productId, product);
    }

    @DeleteMapping("{product-id}")
    @Security
    @Override
    public ProductDto deleteById(@PathVariable(name = "product-id") Long productId) {
        return productService.deleteById(productId);
    }

    @GetMapping("names/{product-name}")
    public ProductDto getByName(@PathVariable(name = "product-name") String productName) {
        return productService.getByName(productName);
    }

    @GetMapping("popular")
    public ProductDto getTheMostPopular() {
        return productService.getTheMostPopular();
    }

    @PostMapping("{product-name}/rules")
    public ProductDto create(@PathVariable(name = "product-name") String productName,
                             @RequestBody List<RuleDto> rules) {
        return productService.post(ProductDto.builder().name(productName).rules(rules).build());
    }
}
