package com.bkap.convert;

import com.bkap.dto.CategoryDto;
import com.bkap.dto.ServicePackageDto;
import com.bkap.entities.Category;
import com.bkap.entities.ServicePackage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicePackageConvert {

    @Autowired
    private ModelMapper modelMapper;

    public ServicePackageDto convertToDto(ServicePackage servicePackageEntity) {
        ServicePackageDto servicePackageDto = modelMapper.map(servicePackageEntity,ServicePackageDto.class);
        if(servicePackageEntity.getRooms() != null){
            servicePackageDto.setTotalRoomByPackage(servicePackageEntity.getRooms().size());
        }
        return servicePackageDto;
    }

    public ServicePackage convertToEntity(ServicePackageDto servicePackageDto) {
        ServicePackage servicePackage = modelMapper.map(servicePackageDto,ServicePackage.class);
        return servicePackage;
    }

    public ServicePackage convertToEntity(ServicePackageDto servicePackageDto, ServicePackage servicePackageOld ) {
        ServicePackage servicePackageNew = modelMapper.map(servicePackageDto,ServicePackage.class);
        servicePackageOld.setTitle(servicePackageNew.getTitle());
        servicePackageOld.setRate(servicePackageNew.getRate());
        servicePackageOld.setAdvantage(servicePackageNew.getAdvantage());
        servicePackageOld.setFit(servicePackageNew.getFit());
        servicePackageOld.setArmorial(servicePackageNew.getArmorial());
        servicePackageOld.setColorTitle(servicePackageNew.getColorTitle());
        servicePackageOld.setDayPrice(servicePackageNew.getDayPrice());
        servicePackageOld.setWeekPrice(servicePackageNew.getWeekPrice());
        servicePackageOld.setMonthPrice(servicePackageNew.getMonthPrice());
        servicePackageOld.setPriceUpNew(servicePackageNew.getPriceUpNew());
        servicePackageOld.setAutoBrowsing(servicePackageNew.getAutoBrowsing());
        servicePackageOld.setTypeTextPackge(servicePackageNew.getTypeTextPackge());
        servicePackageOld.setTagDemo(servicePackageNew.getTagDemo());
        return servicePackageOld;
    }
}
