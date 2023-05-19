package com.bkap.controller;

import com.bkap.dto.PriceAreaDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.PriceAreaService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/ghost/priceArea")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PriceAreaController {

    @Autowired
    private PriceAreaService service;


    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<PriceAreaDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PriceAreaDto> save(@RequestBody PriceAreaDto priceAreaDto){
        return new ResponseEntity<PriceAreaDto>(service.save(priceAreaDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PriceAreaDto> update(@PathVariable("id") int id, @RequestBody PriceAreaDto priceAreaDto) {
        priceAreaDto.setId(id);
        return new ResponseEntity<PriceAreaDto>(service.save(priceAreaDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PriceAreaDto> delete(@PathVariable("id") int id) {
        PriceAreaDto priceAreaDto = service.findById(id);
        if (priceAreaDto != null) {
            service.delete(id);
            return new ResponseEntity<PriceAreaDto>(priceAreaDto, HttpStatus.OK);
        }
        return new ResponseEntity<PriceAreaDto>(new PriceAreaDto(), HttpStatus.NOT_FOUND);
    }
}
