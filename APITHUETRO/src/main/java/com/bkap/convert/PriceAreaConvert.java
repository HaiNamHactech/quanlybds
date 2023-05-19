package com.bkap.convert;

import com.bkap.dto.PriceAreaDto;
import com.bkap.entities.PriceArea;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceAreaConvert {

    @Autowired
    private ModelMapper modelMapper;

    public PriceAreaDto convertToDto(PriceArea priceAreaEntity) {
        PriceAreaDto priceAreaDto = modelMapper.map(priceAreaEntity,PriceAreaDto.class);
        return priceAreaDto;
    }

    public PriceArea convertToEntity(PriceAreaDto priceAreaDto) {
        PriceArea priceArea = modelMapper.map(priceAreaDto,PriceArea.class);
        return priceArea;
    }

    public PriceArea convertToEntity(PriceAreaDto priceAreaDto,PriceArea priceAreaOld ) {
        PriceArea priceAreaNew = modelMapper.map(priceAreaDto,PriceArea.class);
        priceAreaOld.setPriceForm(priceAreaNew.getPriceForm());
        priceAreaOld.setPriceTo(priceAreaNew.getPriceTo());
        priceAreaOld.setAreaFrom(priceAreaNew.getAreaFrom());
        priceAreaOld.setAreaTo(priceAreaNew.getAreaTo());
        priceAreaOld.setTypes(priceAreaNew.getTypes());
        priceAreaOld.setTitle(priceAreaNew.getTitle());
        return priceAreaOld;
    }
}
