package com.oneune.coffee.maker.services;

import com.oneune.coffee.maker.contracts.CRUDable;
import com.oneune.coffee.maker.dtos.ProductDto;
import com.oneune.coffee.maker.dtos.RuleDto;
import com.oneune.coffee.maker.readers.ProductReader;
import com.oneune.coffee.maker.repositories.MaterialRepository;
import com.oneune.coffee.maker.repositories.ProductRepository;
import com.oneune.coffee.maker.repositories.RuleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductService implements CRUDable<ProductDto> {

    ProductReader productReader;
    ProductRepository productRepository;
    MaterialRepository materialRepository;
    RuleRepository ruleRepository;

    @Override
    public ProductDto post(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto getById(Long productId) {
        return null;
    }

    @Override
    public List<ProductDto> search(int page, int size) {
        return null;
    }

    @Override
    public ProductDto put(Long productId, ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto deleteById(Long productId) {
        return null;
    }

    public ProductDto getByName(String productName) {
        ProductDto product = productReader.getByName(productName);
        return null;
    }

    public void validate(RuleDto rule) {

    }

    public ProductDto make(ProductDto product) {
        return null;
    }
}
