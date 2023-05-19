package com.bkap.controller;

import com.bkap.dto.ServicePackageDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.ServicePackageService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/ghost/servicePackage")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServicePackageController {

    @Autowired
    private ServicePackageService service;


    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<ServicePackageDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServicePackageDto> save(@RequestBody ServicePackageDto servicePackageDto){
        return new ResponseEntity<ServicePackageDto>(service.save(servicePackageDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ServicePackageDto> update(@PathVariable("id") int id, @RequestBody ServicePackageDto servicePackageDto) {
        servicePackageDto.setId(id);
        return new ResponseEntity<ServicePackageDto>(service.save(servicePackageDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ServicePackageDto> delete(@PathVariable("id") int id) {
        ServicePackageDto servicePackageDto = service.findById(id);
        if (servicePackageDto != null) {
            service.delete(id);
            return new ResponseEntity<ServicePackageDto>(servicePackageDto, HttpStatus.OK);
        }
        return new ResponseEntity<ServicePackageDto>(new ServicePackageDto(), HttpStatus.NOT_FOUND);
    }
}
