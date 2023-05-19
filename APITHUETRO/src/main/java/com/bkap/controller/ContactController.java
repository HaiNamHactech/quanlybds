package com.bkap.controller;


import com.bkap.dto.ContactDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.ContactService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/ghost/contact")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContactController {

    @Autowired
    private ContactService service;


    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<ContactDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContactDto> save(@RequestBody ContactDto contactDto){
        return new ResponseEntity<ContactDto>(service.save(contactDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ContactDto> update(@PathVariable("id") int id, @RequestBody ContactDto contactDto) {
        contactDto.setId(id);
        return new ResponseEntity<ContactDto>(service.save(contactDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ContactDto> delete(@PathVariable("id") int id) {
        ContactDto contactDto = service.findById(id);
        if (contactDto != null) {
            service.delete(id);
            return new ResponseEntity<ContactDto>(contactDto, HttpStatus.OK);
        }
        return new ResponseEntity<ContactDto>(new ContactDto(), HttpStatus.NOT_FOUND);
    }
}
