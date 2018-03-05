package org.lkwangus.demo.restjpa.backend.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.lkwangus.demo.restjpa.backend.entity.Price;
import org.lkwangus.demo.restjpa.backend.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/price")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceController {

    @NonNull
    PriceService priceService;

    @RequestMapping("findAll")
    public List<Price> findAll() {
        return priceService.findAll();
    }

    @RequestMapping("publish/{value:.+}")
    public Price add(@PathVariable Double value) {
        return priceService.add(value);
    }

    @RequestMapping("latest")
    public List<Price> latest() {
        return priceService.getLatest();
    }

    @RequestMapping("latestWithPage")
    public Page<Price> latestWithPage() {
        return priceService.getLatestWithPage();
    }

    @RequestMapping("latestAverage")
    public Double latestAverage() {
        return priceService.getLatestAverage();
    }
}
