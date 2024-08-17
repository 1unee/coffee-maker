package com.oneune.coffee.maker.repositories;

import com.oneune.coffee.maker.entities.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<RuleEntity, Long> {
}
