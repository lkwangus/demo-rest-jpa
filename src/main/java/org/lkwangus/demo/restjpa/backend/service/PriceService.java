package org.lkwangus.demo.seekers.backend.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.lkwangus.demo.seekers.backend.entity.Price;
import org.lkwangus.demo.seekers.backend.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @NonNull
    PriceRepository priceRepository;

    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    public Price add(Double value) {
        Price price = new Price(null, value, new Date());
        logger.info("[price] price: " + price);
        return priceRepository.saveAndFlush(price);
    }

    public Page<Price> getLatest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        return priceRepository.findAll(pageable);
    }
}
