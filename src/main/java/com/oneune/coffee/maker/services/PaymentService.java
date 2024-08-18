package com.oneune.coffee.maker.services;

import com.oneune.coffee.maker.dtos.PaymentDto;
import com.oneune.coffee.maker.dtos.ProductDto;
import com.oneune.coffee.maker.entities.PaymentEntity;
import com.oneune.coffee.maker.readers.PaymentReader;
import com.oneune.coffee.maker.repositories.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PaymentService {

    ModelMapper modelMapper;
    PaymentRepository paymentRepository;
    PaymentReader paymentReader;

    public PaymentDto beginPayment(ProductDto product) {
        return PaymentDto.builder()
                .product(product)
                .total(product.getPrice())
                .timestamp(Instant.now())
                .success(false)
                .message("Waiting")
                .build();
    }

    @Transactional
    public void finishPayment(PaymentDto payment, boolean success, String message) {
        payment.setSuccess(success);
        payment.setMessage(message);
        PaymentEntity paymentEntity = modelMapper.map(payment, PaymentEntity.class);
        paymentRepository.saveAndFlush(paymentEntity);
    }

    public ProductDto getTheMostPopular() {
        return paymentReader.getTheMostPopular();
    }
}
