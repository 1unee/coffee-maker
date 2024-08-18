package com.oneune.coffee.maker.controllers;

import com.oneune.coffee.maker.aop.annotations.Security;
import com.oneune.coffee.maker.contracts.CRUDable;
import com.oneune.coffee.maker.dtos.MaterialDto;
import com.oneune.coffee.maker.services.MaterialService;
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
@RequestMapping("materials")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MaterialController implements CRUDable<MaterialDto> {

    MaterialService materialService;

    @Operation(
            summary = "Create material",
            description = "Works only with security key from application.yml"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = MaterialDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping
    @Security
    @Override
    public MaterialDto post(@RequestBody MaterialDto material) {
        return materialService.post(material);
    }

    @Operation(summary = "Get material by ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = MaterialDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @GetMapping("{material-id}")
    @Override
    public MaterialDto getById(@PathVariable(name = "material-id") Long materialId) {
        return materialService.getById(materialId);
    }

    @Operation(summary = "Search materials")
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
    public List<MaterialDto> search(@RequestParam int page, @RequestParam int size) {
        return materialService.search(page, size);
    }

    @Operation(
            summary = "Edit material by ID",
            description = "Works only with security key from application.yml"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = MaterialDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @PutMapping("{material-id}")
    @Security
    @Override
    public MaterialDto put(@PathVariable(name = "material-id") Long materialId, @RequestBody MaterialDto material) {
        return materialService.put(materialId, material);
    }

    @Operation(
            summary = "Delete material by ID",
            description = "Works only with security key from application.yml"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = MaterialDto.class),
                            mediaType = "application/json"
                    )
            )
    })
    @DeleteMapping("{material-id}")
    @Security
    @Override
    public MaterialDto deleteById(@PathVariable(name = "material-id") Long materialId) {
        return materialService.deleteById(materialId);
    }
}
