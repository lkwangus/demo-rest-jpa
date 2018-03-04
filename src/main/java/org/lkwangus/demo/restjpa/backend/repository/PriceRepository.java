package org.lkwangus.demo.seekers.backend.repository;

import org.lkwangus.demo.seekers.backend.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long>{
}
