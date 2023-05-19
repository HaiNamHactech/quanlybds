package com.bkap.convert;

import com.bkap.dto.GroupRoleDto;
import com.bkap.entities.GroupRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupRoleConvert {
    @Autowired
    private ModelMapper modelMapper;

    public GroupRoleDto convertToDto(GroupRole groupRoleEntity) {
        GroupRoleDto groupRoleDto = modelMapper.map(groupRoleEntity,GroupRoleDto.class);
        return groupRoleDto;
    }

    public GroupRole convertToEntity(GroupRoleDto groupRoleDto) {
        GroupRole groupRole = modelMapper.map(groupRoleDto,GroupRole.class);
        return groupRole;
    }

    public GroupRole convertToEntity(GroupRoleDto groupRoleDto,GroupRole groupRoleOld) {
        GroupRole groupRoleNew = modelMapper.map(groupRoleDto,GroupRole.class);
        groupRoleOld.setRoleName(groupRoleNew.getRoleName());
        groupRoleOld.setDescription(groupRoleNew.getDescription());
        return groupRoleOld;
    }
}
