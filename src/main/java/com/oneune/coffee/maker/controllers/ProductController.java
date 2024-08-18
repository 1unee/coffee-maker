package com.oneune.coffee.maker.controllers;

import com.oneune.coffee.maker.aop.annotations.Security;
import com.oneune.coffee.maker.contracts.CRUDable;
import com.oneune.coffee.maker.dtos.ProductDto;
import com.oneune.coffee.maker.dtos.RuleDto;
import com.oneune.coffee.maker.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Create product",
            description = "Works only with security key from application.yml"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = ProductDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping
    @Security
    @Override
    public ProductDto post(@RequestBody ProductDto product) {
        return productService.post(product);
    }

    @Operation(summary = "Get product by ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = ProductDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @GetMapping("{product-id}")
    @Override
    public ProductDto getById(@PathVariable(name = "product-id") Long productId) {
        return productService.getById(productId);
    }

    @Operation(summary = "Search products")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = List.class),
                            mediaType = "application/json"
                    )
            )
    })
    @GetMapping("search")
    @Override
    public List<ProductDto> search(@RequestParam int page, @RequestParam int size) {
        return productService.search(page, size);
    }

    @Operation(
            summary = "Edit product by ID",
            description = "Works only with security key from application.yml"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = ProductDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @PutMapping("{product-id}")
    @Security
    @Override
    public ProductDto put(@PathVariable(name = "product-id") Long productId, @RequestBody ProductDto product) {
        return productService.put(productId, product);
    }

    @Operation(
            summary = "Delete product by ID",
            description = "Works only with security key from application.yml"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = ProductDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @DeleteMapping("{product-id}")
    @Security
    @Override
    public ProductDto deleteById(@PathVariable(name = "product-id") Long productId) {
        return productService.deleteById(productId);
    }

    @Operation(summary = "Cook product by name (unique)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = ProductDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @GetMapping("names/{product-name}")
    public ProductDto getByName(@PathVariable(name = "product-name") String productName) {
        return productService.getByName(productName);
    }

    @Operation(summary = "Get the most popular product by history")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = ProductDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @GetMapping("popular")
    public ProductDto getTheMostPopular() {
        return productService.getTheMostPopular();
    }

    @Operation(summary = "Add a new rules for product")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = ProductDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping("{product-name}/rules")
    public ProductDto create(@PathVariable(name = "product-name") String productName,
                             @RequestBody List<RuleDto> rules) {
        return productService.post(ProductDto.builder().name(productName).rules(rules).build());
    }
}
