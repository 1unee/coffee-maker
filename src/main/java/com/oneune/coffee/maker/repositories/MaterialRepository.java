package com.oneune.coffee.maker.repositories;

import com.oneune.coffee.maker.entities.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {
}
