package com.oneune.coffee.maker.repositories;

import com.oneune.coffee.maker.entities.QuantityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityRepository extends JpaRepository<QuantityEntity, Long> {
}
