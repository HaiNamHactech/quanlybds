package com.bkap.convert;

import com.bkap.dto.FunctionDto;
import com.bkap.entities.Function;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FunctionConvert {

    @Autowired
    private ModelMapper modelMapper;

    public FunctionDto convertToDto(Function functionEntity) {
        FunctionDto functionDto = modelMapper.map(functionEntity,FunctionDto.class);

        return functionDto;
    }

    public Function convertToEntity(FunctionDto functionDto) {
        Function function = modelMapper.map(functionDto,Function.class);
        return function;
    }

    public Function convertToEntity(FunctionDto functionDto,Function functionOld) {
        Function functionNew = modelMapper.map(functionDto,Function.class);
        functionOld.setFunctionName(functionNew.getFunctionName());
        functionOld.setUrl(functionNew.getUrl());
        functionOld.setIcon(functionNew.getIcon());
        functionOld.setParentId(functionNew.getParentId());
        functionOld.setSystems(functionNew.getSystems());
        return functionOld;
    }
}
