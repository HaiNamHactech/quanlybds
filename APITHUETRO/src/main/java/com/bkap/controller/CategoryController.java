package com.bkap.controller;

import com.bkap.dto.CategoryDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.CategoryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ghost/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public List<CategoryDto> getAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("id") int id) {
        return new ResponseEntity<CategoryDto>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<CategoryDto>> paginations(@RequestParam("filter") String filter) {
        System.out.println(filter);
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        System.out.println("==================");
        System.out.println(p);
        System.out.println("====================");
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<CategoryDto>(service.save(categoryDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable("id") int id, @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        return new ResponseEntity<CategoryDto>(service.save(categoryDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> delete(@PathVariable("id") int id) {
        CategoryDto categoryDto = service.findById(id);
        if (categoryDto != null) {
            service.delete(id);
            return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
        }
        return new ResponseEntity<CategoryDto>(new CategoryDto(), HttpStatus.NOT_FOUND);
    }

}
