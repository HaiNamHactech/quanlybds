package com.bkap.controller;

import com.bkap.dto.FavouriteDto;
import com.bkap.dto.UserRoleDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.FavouriteService;
import com.bkap.utils.service.impl.UserRoleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user/favourite")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FavouriteController {

    @Autowired
    private FavouriteService service;

    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<FavouriteDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FavouriteDto> save(@RequestBody FavouriteDto favouriteDto){
        return new ResponseEntity<FavouriteDto>(service.save(favouriteDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FavouriteDto> update(@PathVariable("id") int id, @RequestBody FavouriteDto favouriteDto) {
        favouriteDto.setId(id);
        return new ResponseEntity<FavouriteDto>(service.save(favouriteDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FavouriteDto> delete(@PathVariable("id") int id) {
        FavouriteDto favouriteDto = service.findById(id);
        if (favouriteDto != null) {
            service.delete(id);
            return new ResponseEntity<FavouriteDto>(favouriteDto, HttpStatus.OK);
        }
        return new ResponseEntity<FavouriteDto>(new FavouriteDto(), HttpStatus.NOT_FOUND);
    }
}
