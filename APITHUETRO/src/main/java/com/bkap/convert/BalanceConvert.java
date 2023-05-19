package com.bkap.convert;

import com.bkap.dto.BalancesDto;
import com.bkap.entities.Balances;
import com.bkap.repositories.CategoryRepository;
import com.bkap.repositories.RoomRepository;
import com.bkap.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanceConvert {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RoomRepository roomRepository;

    public BalancesDto convertToDto(Balances balancesEntity) {
        BalancesDto balancesDto = modelMapper.map(balancesEntity,BalancesDto.class);
        balancesDto.setCategoryId(balancesEntity.getCategory().getId());
        balancesDto.setCategoryName(balancesEntity.getCategory().getName());
        balancesDto.setUserId(balancesEntity.getUser().getId());
        balancesDto.setRoomId(balancesEntity.getRoom().getId());
        balancesDto.setRoomName(balancesEntity.getRoom().getTitle());
        return balancesDto;
    }

    public Balances convertToEntity(BalancesDto balancesDto){
        Balances balances = modelMapper.map(balancesDto,Balances.class);
        balances.setRoom(roomRepository.findById(balancesDto.getRoomId()).get());
        balances.setCategory(categoryRepository.findById(balancesDto.getCategoryId()).get());
        balances.setUser(userRepository.findById(balancesDto.getUserId()).get());
        return balances;
    }

    public Balances convertToEntity(BalancesDto balancesDto, Balances balancesOld ) {
        Balances balancesNew = modelMapper.map(balancesDto,Balances.class);
        balancesNew.setRoom(roomRepository.findById(balancesOld.getRoom().getId()).get());
        balancesNew.setCategory(categoryRepository.findById(balancesOld.getCategory().getId()).get());
        balancesNew.setUser(userRepository.findById(balancesOld.getUser().getId()).get());
        return balancesNew;
    }
}
