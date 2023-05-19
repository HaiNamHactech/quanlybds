package com.bkap.utils.service.impl;

import com.bkap.convert.PriceAreaConvert;
import com.bkap.dto.PriceAreaDto;
import com.bkap.entities.PriceArea;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.PriceAreaRepository;
import com.bkap.utils.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PriceAreaService implements IGeneralService<PriceAreaDto, BaseFilter> {

    @Autowired
    private PriceAreaRepository priceAreaRepository;

    @Autowired
    private PriceAreaConvert convert;

    @Override
    public List<PriceAreaDto> findAll() {
        return null;
    }

    @Override
    public Page<PriceAreaDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = null ;
        if(filter.getPageSize() == 0){
            pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
        }
        else {
            pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        }

        Page<PriceArea> pagePriceAreaEntitys = priceAreaRepository.findAll(new Specification<PriceArea>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<PriceArea> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                filter.getColumnFilters().forEach((key,value) -> {
                    if(key != null && !value.equals("")){
                    if(key.endsWith("!ext")){
                        String ekey = key.replace("!ext", "");
                        predicates.add(criteriaBuilder.equal(root.get(ekey), value));
                    }
                    else {
                        predicates.add(criteriaBuilder.like(root.get(key).as(String.class), "%" + value + "%"));
                    }
                    }
                });
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        }, pageable);
        return pagePriceAreaEntitys.map(pagePriceArea -> convert.convertToDto(pagePriceArea));
    }

    @Override
    public PriceAreaDto findById(int id) {
        return null;
    }

    @Override
    public PriceAreaDto save(PriceAreaDto priceAreaDto) {
        PriceArea priceArea = new PriceArea();
        if (priceAreaDto.getId() != 0) {
            PriceArea priceAreaOld = priceAreaRepository.findById(priceAreaDto.getId()).orElseThrow(
                    () -> new NotFoundException("Not Found with id : " + priceAreaDto.getId())
            );
            priceArea = priceAreaRepository.save(convert.convertToEntity(priceAreaDto, priceAreaOld));

        }
        else {
            priceArea = priceAreaRepository.save(convert.convertToEntity(priceAreaDto));
        }
        return convert.convertToDto(priceArea);
    }

    @Override
    public void delete(int id) {
        priceAreaRepository.deleteById(id);
    }
}
