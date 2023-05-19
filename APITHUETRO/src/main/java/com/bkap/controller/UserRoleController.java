package com.bkap.controller;

import com.bkap.dto.UserRoleDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.UserRoleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user/userRole")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRoleController {

    @Autowired
    private UserRoleService service;

    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<UserRoleDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserRoleDto> save(@RequestBody UserRoleDto userRoleDto){
        return new ResponseEntity<UserRoleDto>(service.save(userRoleDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserRoleDto> update(@PathVariable("id") int id, @RequestBody UserRoleDto userRoleDto) {
        userRoleDto.setId(id);
        return new ResponseEntity<UserRoleDto>(service.save(userRoleDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserRoleDto> delete(@PathVariable("id") int id) {
        UserRoleDto userRoleDto = service.findById(id);
        if (userRoleDto != null) {
            service.delete(id);
            return new ResponseEntity<UserRoleDto>(userRoleDto, HttpStatus.OK);
        }
        return new ResponseEntity<UserRoleDto>(new UserRoleDto(), HttpStatus.NOT_FOUND);
    }
}
