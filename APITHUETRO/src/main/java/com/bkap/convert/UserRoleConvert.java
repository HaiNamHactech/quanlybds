package com.bkap.convert;

import com.bkap.dto.UserDto;
import com.bkap.dto.UserRoleDto;
import com.bkap.entities.User;
import com.bkap.entities.UserRole;
import com.bkap.repositories.GroupRoleRepository;
import com.bkap.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConvert {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRoleRepository groupRoleRepository;

    public UserRoleDto convertToDto(UserRole userRoleEntity) {
        UserRoleDto userRoleDto = modelMapper.map(userRoleEntity,UserRoleDto.class);
        userRoleDto.setGroupRoleId(userRoleEntity.getGroupRole().getId());
        userRoleDto.setUserId(userRoleEntity.getUser().getId());
        return userRoleDto;
    }

    public UserRole convertToEntity(UserRoleDto userRoleDto){
        UserRole userRole = modelMapper.map(userRoleDto,UserRole.class);
        userRole.setUser(userRepository.findById(userRoleDto.getUserId()).get());
        userRole.setGroupRole(groupRoleRepository.findById(userRoleDto.getGroupRoleId()).get());
        return userRole;
    }

    public UserRole convertToEntity(UserRoleDto userRoleDto, UserRole userRoleOld ) {
        userRoleOld.setUser(userRepository.findById(userRoleDto.getUserId()).get());
        userRoleOld.setGroupRole(groupRoleRepository.findById(userRoleDto.getGroupRoleId()).get());
        return userRoleOld;
    }
}
