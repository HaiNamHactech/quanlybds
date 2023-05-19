package com.bkap.convert;

import com.bkap.dto.CategoryDto;
import com.bkap.dto.UserDto;
import com.bkap.entities.Category;
import com.bkap.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {

    @Autowired
    private ModelMapper modelMapper;

    public UserDto convertToDto(User userEntity) {
        UserDto userDto = modelMapper.map(userEntity,UserDto.class);
        userDto.setPass(null);
        return userDto;
    }

    public User convertToEntity(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
        return user;
    }

    public User convertToEntity(UserDto userDto, User userOld ) {
        User userNew = modelMapper.map(userDto,User.class);
        if(userNew.getName() != null){
            userOld.setName(userNew.getName());
        }
        if(userNew.getPhone() != null){
            userOld.setPhone(userNew.getPhone());
        }
        if(userNew.getEmail() != null){
            userOld.setEmail(userNew.getEmail());
        }
        if(userNew.getPass() != null){
            userOld.setPass(userNew.getPass());
        }
        if(userNew.getAvatar() != null){
            userOld.setAvatar(userNew.getAvatar());
        }
        if(userNew.getAccountBalance() != 0){
            userOld.setAccountBalance(userNew.getAccountBalance());
        }

        return userOld;
    }
}
