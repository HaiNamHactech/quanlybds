package com.bkap.convert;

import com.bkap.dto.InstructDto;
import com.bkap.entities.Instruct;
import com.bkap.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstructConvert {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public InstructDto convertToDto(Instruct instructEntity) {
        InstructDto instructDto = modelMapper.map(instructEntity,InstructDto.class);
        instructDto.setUserId(instructEntity.getUser().getId());
        return instructDto;
    }

    public Instruct convertToEntity(InstructDto instructDto) {
        Instruct instruct = modelMapper.map(instructDto,Instruct.class);
        instruct.setUser(userRepository.findById(instructDto.getUserId()).get());
        return instruct;
    }

    public Instruct convertToEntity(InstructDto instructDto,Instruct instructOld ) {
        Instruct instructNew = modelMapper.map(instructDto,Instruct.class);
        instructOld.setTypes(instructNew.getTypes());
        instructOld.setDescription(instructNew.getDescription());
        instructOld.setUser(userRepository.findById(instructDto.getUserId()).get());
        instructOld.setCreateAt(instructNew.getCreateAt());
        instructOld.setUpdateAt(instructNew.getUpdateAt());
        instructOld.setTitle(instructNew.getTitle());
        instructOld.setVideoLink(instructNew.getVideoLink());
        instructOld.setImage(instructNew.getImage());
        instructOld.setTagContent(instructNew.getTagContent());
        instructOld.setRate(instructNew.getRate());
        return instructOld;
    }
}
