package com.bkap.convert;

import com.bkap.dto.RoomDto;
import com.bkap.entities.Room;
import com.bkap.repositories.CategoryRepository;
import com.bkap.repositories.ServicePackageRespository;
import com.bkap.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomConvert {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRespository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicePackageRespository servicePackageRespository;

    public RoomDto convertToDto(Room roomEntity){
        RoomDto roomDto = modelMapper.map(roomEntity,RoomDto.class);
        roomDto.setCategoryId(roomEntity.getCategory().getId());
        roomDto.setPackageId(roomEntity.getServicePackage().getId());
        roomDto.setRateService(roomEntity.getServicePackage().getRate());
        roomDto.setTitleService(roomEntity.getServicePackage().getTitle());
        roomDto.setColorTilteService(roomEntity.getServicePackage().getColorTitle());
        roomDto.setUserId(roomEntity.getUser().getId());
        roomDto.setPhoneNumber(roomEntity.getUser().getPhone());
        roomDto.setAvatarUser(roomEntity.getUser().getAvatar());
        roomDto.setCategoryName(roomEntity.getCategory().getName());
        return roomDto;
    }

    public Room convertToEntity(RoomDto roomDto) {
        Room room = modelMapper.map(roomDto,Room.class);
        room.setCategory(categoryRespository.findById(roomDto.getCategoryId()).get());
        room.setServicePackage(servicePackageRespository.findById(roomDto.getPackageId()).get());
        room.setUser(userRepository.findById(roomDto.getUserId()).get());
        return room;
    }

    public Room convertToEntity(RoomDto roomDto,Room roomOld){
        Room roomNew = modelMapper.map(roomDto,Room.class);
        roomOld.setTitle(roomNew.getTitle());
        roomOld.setSlug(roomNew.getSlug());
        roomOld.setAvatar(roomNew.getAvatar());
        roomOld.setDescription(roomNew.getDescription());
        roomOld.setDistrictId(roomNew.getDistrictId());
        roomOld.setConsciousId(roomNew.getConsciousId());
        roomOld.setWardId(roomNew.getWardId());
        roomOld.setPrice(roomNew.getPrice());
        roomOld.setArea(roomNew.getArea());
        roomOld.setDateSubmited(roomNew.getDateSubmited());
        roomOld.setDateExpiration(roomNew.getDateExpiration());
        roomOld.setSpecificAddress(roomNew.getSpecificAddress());
        roomOld.setMap(roomNew.getMap());
        roomOld.setSubjectId(roomNew.getSubjectId());
        roomOld.setVideoLink(roomNew.getVideoLink());
        roomOld.setStatus(roomNew.getStatus());
        roomOld.setConsciousName(roomNew.getConsciousName());
        roomOld.setWardName(roomNew.getWardName());
        roomOld.setDistrictName(roomNew.getDistrictName());
        roomOld.setCategory( categoryRespository.findById(roomDto.getCategoryId()).get());
        roomOld.setServicePackage(servicePackageRespository.findById(roomDto.getPackageId()).get());
        roomOld.setListImgExtra(roomNew.getListImgExtra());
        roomOld.setUser(userRepository.findById(roomDto.getUserId()).get());
        roomOld.setSortDescription(roomNew.getSortDescription());
        return  roomOld;
    }

    public Room convertToEntityHiden(RoomDto roomDto,Room roomOld){
        Room roomNew = modelMapper.map(roomDto,Room.class);
        roomOld.setStatus(roomNew.getStatus());
        return  roomOld;
    }


}
