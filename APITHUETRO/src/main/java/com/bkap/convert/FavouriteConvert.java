package com.bkap.convert;

import com.bkap.dto.FavouriteDto;
import com.bkap.entities.Favourite;
import com.bkap.repositories.RoomRepository;
import com.bkap.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FavouriteConvert {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    public FavouriteDto convertToDto(Favourite favouriteEntity) {
        FavouriteDto favouriteDto = modelMapper.map(favouriteEntity,FavouriteDto.class);
        favouriteDto.setUserId(favouriteEntity.getUser().getId());
        favouriteDto.setRoomId(favouriteEntity.getRoom().getId());
        return favouriteDto;
    }

    public Favourite convertToEntity(FavouriteDto favouriteDto) {
        Favourite favourite = modelMapper.map(favouriteDto,Favourite.class);
        favourite.setUser(userRepository.findById(favouriteDto.getUserId()).get());
        favourite.setRoom(roomRepository.findById(favouriteDto.getRoomId()).get());
        return favourite;
    }

    public Favourite convertToEntity(FavouriteDto favouriteDto,Favourite favouriteOld ) {
        favouriteOld.setUser(userRepository.findById(favouriteDto.getUserId()).get());
        favouriteOld.setRoom(roomRepository.findById(favouriteDto.getRoomId()).get());
        return favouriteOld;
    }
}
