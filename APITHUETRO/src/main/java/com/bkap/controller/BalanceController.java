package com.bkap.controller;

import com.bkap.dto.BalancesDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.BalancesService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user/balances")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BalanceController {

    @Autowired
    private BalancesService service;

    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<BalancesDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BalancesDto> save(@RequestBody BalancesDto balancesDto){
        return new ResponseEntity<BalancesDto>(service.save(balancesDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BalancesDto> update(@PathVariable("id") int id, @RequestBody BalancesDto balancesDto) {
        balancesDto.setId(id);
        return new ResponseEntity<BalancesDto>(service.save(balancesDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BalancesDto> delete(@PathVariable("id") int id) {
        BalancesDto balancesDto = service.findById(id);
        if (balancesDto != null) {
            service.delete(id);
            return new ResponseEntity<BalancesDto>(balancesDto, HttpStatus.OK);
        }
        return new ResponseEntity<BalancesDto>(new BalancesDto(), HttpStatus.NOT_FOUND);
    }

}
