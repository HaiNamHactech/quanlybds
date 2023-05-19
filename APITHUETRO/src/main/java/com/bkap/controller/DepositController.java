package com.bkap.controller;
import com.bkap.dto.DepositDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.DepositService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user/deposit")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepositController {

    @Autowired
    private DepositService service;

    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<DepositDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepositDto> save(@RequestBody DepositDto depositDto){
        return new ResponseEntity<DepositDto>(service.save(depositDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DepositDto> update(@PathVariable("id") int id, @RequestBody DepositDto depositDto) {
        depositDto.setId(id);
        return new ResponseEntity<DepositDto>(service.save(depositDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DepositDto> delete(@PathVariable("id") int id) {
        DepositDto depositDto = service.findById(id);
        if (depositDto != null) {
            service.delete(id);
            return new ResponseEntity<DepositDto>(depositDto, HttpStatus.OK);
        }
        return new ResponseEntity<DepositDto>(new DepositDto(), HttpStatus.NOT_FOUND);
    }
}
