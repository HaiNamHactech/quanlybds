package com.bkap.controller;

import com.bkap.dto.UserDto;
import com.bkap.entities.LoginRequest;
import com.bkap.filter.BaseFilter;
import com.bkap.utils.JWTUntil;
import com.bkap.utils.MyUser;
import com.bkap.utils.UserData;
import com.bkap.utils.service.impl.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUntil jwtUntil;

    @PostMapping("/login")
    public ResponseEntity<UserData> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        MyUser detail = (MyUser) authentication.getPrincipal();
        // Trả về jwt cho người dùng.
        String jwt = jwtUntil.generateToken(detail);
//        Token token = new Token();
//        token.setCode(jwt);
        return ResponseEntity.ok(new UserData(jwt,detail.getUser().getId(),detail.getUser().getName(),detail.getUser().getEmail(),detail.getUser().getPhone(),detail.getUser().getAvatar()));
    }

    @GetMapping(value = "/paginations")
    public ResponseEntity<Page<UserDto>> paginations(@RequestParam("filter") String filter) {
        BaseFilter p = new Gson().fromJson(filter, BaseFilter.class);
        return new ResponseEntity<>(service.paginations(p), HttpStatus.OK);
    }
    @GetMapping(value = "/balance")
    public ResponseEntity<Object> getBalance(@RequestHeader(value = "Authorization", required = true) String token) {
        if(token.startsWith("Bearer ")) {
            String codeToken = token.substring(7);
            jwtUntil.validateToken(codeToken);
            Integer userId = jwtUntil.getUserByIdfromJWT(codeToken);
            return new ResponseEntity<>(service.getaccountBalance(userId), HttpStatus.OK);
        }
        return null;

    }

    @GetMapping(value="/getUser")
    public ResponseEntity<UserDto> getUserById(@RequestHeader(value = "Authorization", required = true) String token){
        if(token.startsWith("Bearer ")) {
            String codeToken = token.substring(7);
            jwtUntil.validateToken(codeToken);
            Integer userId = jwtUntil.getUserByIdfromJWT(codeToken);
            return new ResponseEntity<UserDto>(service.findById(userId), HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto){
        return new ResponseEntity<UserDto>(service.save(userDto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") int id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        return new ResponseEntity<UserDto>(service.save(userDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable("id") int id) {
        UserDto userDto = service.findById(id);
        if (userDto != null) {
            service.delete(id);
            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<UserDto>(new UserDto(), HttpStatus.NOT_FOUND);
    }
}
