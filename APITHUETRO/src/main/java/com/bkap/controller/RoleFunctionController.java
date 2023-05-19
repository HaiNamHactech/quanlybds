package com.bkap.controller;

import com.bkap.dto.RoleFunctionDto;
import com.bkap.entities.RoleFunction;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.RoleFunctionService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user/roleFunction")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleFunctionController {

    @Autowired
    private RoleFunctionService service;

    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<RoleFunctionDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleFunctionDto> save(@RequestBody RoleFunctionDto roleFunctionDto){
        return new ResponseEntity<RoleFunctionDto>(service.save(roleFunctionDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleFunctionDto> update(@PathVariable("id") int id, @RequestBody RoleFunctionDto roleFunctionDto) {
        roleFunctionDto.setId(id);
        return new ResponseEntity<RoleFunctionDto>(service.save(roleFunctionDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RoleFunctionDto> delete(@PathVariable("id") int id) {
        RoleFunctionDto roleFunctionDto = service.findById(id);
        if (roleFunctionDto != null) {
            service.delete(id);
            return new ResponseEntity<RoleFunctionDto>(roleFunctionDto, HttpStatus.OK);
        }
        return new ResponseEntity<RoleFunctionDto>(new RoleFunctionDto(), HttpStatus.NOT_FOUND);
    }
}
