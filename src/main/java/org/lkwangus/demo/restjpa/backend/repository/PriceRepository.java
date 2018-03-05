package org.lkwangus.demo.restjpa.backend.repository;

import org.lkwangus.demo.restjpa.backend.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
