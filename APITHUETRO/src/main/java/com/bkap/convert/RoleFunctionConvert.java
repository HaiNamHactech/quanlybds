package com.bkap.convert;

import com.bkap.dto.RoleFunctionDto;
import com.bkap.dto.UserDto;
import com.bkap.entities.RoleFunction;
import com.bkap.entities.User;
import com.bkap.repositories.FunctionRepository;
import com.bkap.repositories.GroupRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleFunctionConvert {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GroupRoleRepository groupRoleRepository;

    @Autowired
    private FunctionRepository functionRepository;

    public RoleFunctionDto convertToDto(RoleFunction roleFunctionEntity) {
        RoleFunctionDto roleFunctionDto = modelMapper.map(roleFunctionEntity,RoleFunctionDto.class);
        roleFunctionDto.setFunctionId(roleFunctionEntity.getFunction().getId());
        roleFunctionDto.setGroupRoleId(roleFunctionEntity.getGroupRole().getId());
        return roleFunctionDto;
    }

    public RoleFunction convertToEntity(RoleFunctionDto roleFunctionDto){
        RoleFunction roleFunction = modelMapper.map(roleFunctionDto,RoleFunction.class);
        roleFunction.setGroupRole(groupRoleRepository.findById(roleFunctionDto.getGroupRoleId()).get());
        roleFunction.setFunction(functionRepository.findById(roleFunctionDto.getFunctionId()).get());
        return roleFunction;
    }

    public RoleFunction convertToEntity(RoleFunctionDto roleFunctionDto, RoleFunction roleFunctionOld ) {
        RoleFunction roleFunctionNew = modelMapper.map(roleFunctionDto,RoleFunction.class);
        roleFunctionOld.setAllChildFunction(roleFunctionNew.getAllChildFunction());
        roleFunctionOld.setC(roleFunctionNew.getC());
        roleFunctionOld.setR(roleFunctionNew.getR());
        roleFunctionOld.setU(roleFunctionNew.getU());
        roleFunctionOld.setD(roleFunctionNew.getD());
        roleFunctionOld.setGroupRole(groupRoleRepository.findById(roleFunctionDto.getGroupRoleId()).get());
        roleFunctionOld.setFunction(functionRepository.findById(roleFunctionDto.getFunctionId()).get());
        return roleFunctionOld;
    }
}
