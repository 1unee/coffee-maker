package com.oneune.coffee.maker.contracts;

import com.oneune.coffee.maker.dtos.AbstractDto;

import java.util.List;

/**
 * Common CRUD contract
 * @param <D> dto
 */
public interface CRUDable<D extends AbstractDto> {
    D post(D dto);
    D getById(Long dtoId);
    List<D> search(int page, int size);
    D put(Long dtoId, D dto);
    D deleteById(Long dtoId);
}
