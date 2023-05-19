package com.bkap.controller;


import com.bkap.dto.ContactDto;
import com.bkap.dto.InstructDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.InstructService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/ghost/instruct")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InstructController {

    @Autowired
    private InstructService service;


    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<InstructDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InstructDto> save(@RequestBody InstructDto instructDto){
        return new ResponseEntity<InstructDto>(service.save(instructDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<InstructDto> update(@PathVariable("id") int id, @RequestBody InstructDto instructDto) {
        instructDto.setId(id);
        return new ResponseEntity<InstructDto>(service.save(instructDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<InstructDto> delete(@PathVariable("id") int id) {
        InstructDto instructDto = service.findById(id);
        if (instructDto != null) {
            service.delete(id);
            return new ResponseEntity<InstructDto>(instructDto, HttpStatus.OK);
        }
        return new ResponseEntity<InstructDto>(new InstructDto(), HttpStatus.NOT_FOUND);
    }
}
