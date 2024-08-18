package com.oneune.coffee.maker.services;

import com.oneune.coffee.maker.contracts.CRUDable;
import com.oneune.coffee.maker.dtos.PaymentDto;
import com.oneune.coffee.maker.dtos.ProductDto;
import com.oneune.coffee.maker.entities.ProductEntity;
import com.oneune.coffee.maker.readers.ProductReader;
import com.oneune.coffee.maker.repositories.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductService implements CRUDable<ProductDto> {

    ModelMapper modelMapper;
    ProductRepository productRepository;
    ProductReader productReader;
    QuantityService quantityService;
    PaymentService paymentService;

    @Transactional
    @Override
    public ProductDto post(ProductDto productDto) {
        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
        productRepository.saveAndFlush(productEntity);
        return productReader.get(productEntity.getId());
    }

    @Override
    public ProductDto getById(Long productId) {
        return productReader.get(productId);
    }

    @Override
    public List<ProductDto> search(int page, int size) {
        return productReader.search(page, size);
    }

    @Transactional
    @Override
    public ProductDto put(Long productId, ProductDto productDto) {
        ProductEntity productEntity = productReader.getEntity(productId);
        modelMapper.map(productDto, productEntity);
        productRepository.saveAndFlush(productEntity);
        return productReader.get(productId);
    }

    @Transactional
    @Override
    public ProductDto deleteById(Long productId) {
        ProductDto product = productReader.get(productId);
        productRepository.deleteById(productId);
        productRepository.flush();
        return product;
    }

    public ProductDto getByName(String productName) {
        ProductDto product = productReader.getByName(productName);
        PaymentDto payment = paymentService.beginPayment(product);

        boolean quantitiesValidationResult = quantityService.validate(product.getRules());
        if (!quantitiesValidationResult) {
            String errorMessage = "Not enough materials!";
            paymentService.finishPayment(payment, false, errorMessage);
            throw new IllegalStateException(errorMessage);
        }

        quantityService.spend(product.getRules());
        paymentService.finishPayment(payment, true, "Finished at %s".formatted(LocalDateTime.now()));
        return productReader.getByName(productName);
    }

    public ProductDto getTheMostPopular() {
        return paymentService.getTheMostPopular();
    }
}
