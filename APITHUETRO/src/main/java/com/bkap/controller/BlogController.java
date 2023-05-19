package com.bkap.controller;


import com.bkap.dto.BlogDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.BlogService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/ghost/blog")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BlogController {

    @Autowired
    private BlogService service;


    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<BlogDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlogDto> save(@RequestBody BlogDto blogDto){
        return new ResponseEntity<BlogDto>(service.save(blogDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BlogDto> update(@PathVariable("id") int id, @RequestBody BlogDto blogDto) {
        blogDto.setId(id);
        return new ResponseEntity<BlogDto>(service.save(blogDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BlogDto> delete(@PathVariable("id") int id) {
        BlogDto blogDto = service.findById(id);
        if (blogDto != null) {
            service.delete(id);
            return new ResponseEntity<BlogDto>(blogDto, HttpStatus.OK);
        }
        return new ResponseEntity<BlogDto>(new BlogDto(), HttpStatus.NOT_FOUND);
    }

}
