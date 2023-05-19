package com.bkap.controller;


import com.bkap.dto.CategoryDto;
import com.bkap.dto.FunctionDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.CategoryService;
import com.bkap.utils.service.impl.FunctionService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user/function")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FunctionController {


    @Autowired
    private FunctionService service;


    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<FunctionDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FunctionDto> save(@RequestBody FunctionDto functionDto) {
        return new ResponseEntity<FunctionDto>(service.save(functionDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FunctionDto> update(@PathVariable("id") int id, @RequestBody FunctionDto functionDto) {
        functionDto.setId(id);
        return new ResponseEntity<FunctionDto>(service.save(functionDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FunctionDto> delete(@PathVariable("id") int id) {
        FunctionDto functionDto = service.findById(id);
        if (functionDto != null) {
            service.delete(id);
            return new ResponseEntity<FunctionDto>(functionDto, HttpStatus.OK);
        }
        return new ResponseEntity<FunctionDto>(new FunctionDto(), HttpStatus.NOT_FOUND);
    }
}
