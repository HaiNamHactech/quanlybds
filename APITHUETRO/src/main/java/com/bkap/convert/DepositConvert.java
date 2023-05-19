package com.bkap.convert;

import com.bkap.dto.BalancesDto;
import com.bkap.dto.DepositDto;
import com.bkap.entities.Balances;
import com.bkap.entities.Deposit;
import com.bkap.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositConvert {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public DepositDto convertToDto(Deposit depositEntity) {
        DepositDto depositDto = modelMapper.map(depositEntity,DepositDto.class);
        depositDto.setUserId(depositEntity.getUser().getId());
        return depositDto;
    }

    public Deposit convertToEntity(DepositDto depositDto){
        Deposit deposit = modelMapper.map(depositDto,Deposit.class);
        deposit.setUser(userRepository.findById(depositDto.getUserId()).get());
        return deposit;
    }

    public Deposit convertToEntity(DepositDto depositDto, Deposit depositOld ) {
        Deposit depositNew = modelMapper.map(depositDto,Deposit.class);
        depositNew.setUser(userRepository.findById(depositOld.getUser().getId()).get());
        return depositNew;
    }

}
