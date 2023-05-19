package com.bkap.controller;

import com.bkap.dto.GroupRoleDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.GroupRoleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user/role")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GroupRoleController {

    @Autowired
    private GroupRoleService service;

    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<GroupRoleDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GroupRoleDto> save(@RequestBody GroupRoleDto groupRoleDto){
        return new ResponseEntity<GroupRoleDto>(service.save(groupRoleDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GroupRoleDto> update(@PathVariable("id") int id, @RequestBody GroupRoleDto groupRoleDto) {
        groupRoleDto.setId(id);
        return new ResponseEntity<GroupRoleDto>(service.save(groupRoleDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GroupRoleDto> delete(@PathVariable("id") int id) {
        GroupRoleDto groupRoleDto = service.findById(id);
        if (groupRoleDto != null) {
            service.delete(id);
            return new ResponseEntity<GroupRoleDto>(groupRoleDto, HttpStatus.OK);
        }
        return new ResponseEntity<GroupRoleDto>(new GroupRoleDto(), HttpStatus.NOT_FOUND);
    }
}
