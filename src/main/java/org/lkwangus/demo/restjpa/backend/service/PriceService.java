package org.lkwangus.demo.restjpa.backend.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.util.MathUtils;
import org.lkwangus.demo.restjpa.backend.entity.Price;
import org.lkwangus.demo.restjpa.backend.repository.PriceRepository;
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

    @NonNull
    RabbitMQService rabbitMQService;

    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    public Price add(Double value) {
        Price price = new Price(null, value, new Date());
        logger.info("[price] price: " + price);
        price = priceRepository.saveAndFlush(price);
        rabbitMQService.send(price);
        return price;
    }

    public Double getLatestAverage() {
        List<Price> prices = this.getLatestWithPage().getContent();
        Double sum = prices.stream().mapToDouble(p -> p.getValue()).sum();
        Double average = sum / prices.size();
        return average;
    }

    public List<Price> getLatest() {
        return this.getLatestWithPage().getContent();
    }

    public Page<Price> getLatestWithPage() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        return priceRepository.findAll(pageable);
    }
}
