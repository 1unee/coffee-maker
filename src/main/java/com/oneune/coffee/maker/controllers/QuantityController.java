package com.oneune.coffee.maker.controllers;

import com.oneune.coffee.maker.aop.annotations.Security;
import com.oneune.coffee.maker.contracts.CRUDable;
import com.oneune.coffee.maker.dtos.QuantityDto;
import com.oneune.coffee.maker.services.QuantityService;
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
@RequestMapping("quantities")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class QuantityController implements CRUDable<QuantityDto> {

    QuantityService quantityService;

    @Operation(
            summary = "Create quantity",
            description = "Works only with security key from application.yml"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = QuantityDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping
    @Security
    @Override
    public QuantityDto post(@RequestBody QuantityDto quantity) {
        return quantityService.post(quantity);
    }

    @Operation(
            summary = "Get quantity by ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = QuantityDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @GetMapping("{quantity-id}")
    @Override
    public QuantityDto getById(@PathVariable(name = "quantity-id") Long quantityId) {
        return quantityService.getById(quantityId);
    }

    @Operation(
            summary = "Search quantities"
    )
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
    public List<QuantityDto> search(@RequestParam int page, @RequestParam int size) {
        return quantityService.search(page, size);
    }

    @Operation(
            summary = "Edit quantity by ID",
            description = "Works only with security key from application.yml"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = QuantityDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @PutMapping("{quantity-id}")
    @Security
    @Override
    public QuantityDto put(@PathVariable(name = "quantity-id") Long quantityId, QuantityDto quantity) {
        return quantityService.put(quantityId, quantity);
    }

    @Operation(
            summary = "Delete quantity by ID",
            description = "Works only with security key from application.yml"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = QuantityDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @DeleteMapping("{quantity-id}")
    @Security
    @Override
    public QuantityDto deleteById(@PathVariable(name = "quantity-id") Long quantityId) {
        return quantityService.deleteById(quantityId);
    }
}
