package com.bkap.controller;

import com.bkap.dto.RoomDto;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.service.impl.RoomService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/ghost/room")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoomController {

    @Autowired
    private RoomService service;

    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<RoomDto>> paginations(@RequestParam("filter") String filter) {
        System.out.println(filter);
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomDto> save(@RequestBody RoomDto roomDto){
        System.out.println(roomDto);
        return new ResponseEntity<RoomDto>(service.save(roomDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoomDto> update(@PathVariable("id") int id, @RequestBody RoomDto roomDto) {
        roomDto.setId(id);
        return new ResponseEntity<RoomDto>(service.save(roomDto), HttpStatus.OK);
    }

    @PutMapping(value = "hiden/{id}")
    public ResponseEntity<RoomDto> hiden(@PathVariable("id") int id, @RequestBody RoomDto roomDto) {
        roomDto.setId(id);
        return new ResponseEntity<RoomDto>(service.hiden(roomDto), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RoomDto> delete(@PathVariable("id") int id) {
        RoomDto roomDto = service.findById(id);
        if (roomDto != null) {
            service.delete(id);
            return new ResponseEntity<RoomDto>(roomDto, HttpStatus.OK);
        }
        return new ResponseEntity<RoomDto>(new RoomDto(), HttpStatus.NOT_FOUND);
    }
}
