package com.bkap.utils.service.impl;

import com.bkap.convert.BalanceConvert;
import com.bkap.dto.BalancesDto;
import com.bkap.entities.Balances;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.BalancesRepository;
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
public class BalancesService implements IGeneralService<BalancesDto, BaseFilter> {

    @Autowired
    private BalancesRepository balancesRepository;

    @Autowired
    private BalanceConvert convert;

    @Override
    public List<BalancesDto> findAll() {
        return null;
    }

    @Override
    public Page<BalancesDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<Balances> pageBalances = balancesRepository.findAll(new Specification<Balances>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Balances> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                filter.getColumnFilters().forEach((key,value) -> {
                    if(key != null && !value.equals("")) {
                        if(key != null && !value.equals("")) {
                            if(key.equals("userId")){
                                predicates.add(criteriaBuilder.equal(root.get("user").get("id"),value));
                            }
                            else {

                                if (key.endsWith("Ext")) {
                                    String ekey = key.replace("Ext", "");
                                    predicates.add(criteriaBuilder.equal(root.get(ekey), value));
                                } else {
                                    predicates.add(criteriaBuilder.like(root.get(key).as(String.class), "%" + value + "%"));
                                }
                            }
                        }
                    }
                });
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
        return pageBalances.map(page -> convert.convertToDto(page));
    }

    @Override
    public BalancesDto findById(int id) {
        return convert.convertToDto(
                balancesRepository.findById(id).
                        orElseThrow(() -> new NotFoundException("Balances Not Found with id : " + id)));
    }

    @Override
    public BalancesDto save(BalancesDto balancesDto) {
        Balances balances = new Balances();
        if (balancesDto.getId() != 0) {
            Balances balancesOld = balancesRepository.findById(balancesDto.getId()).orElseThrow(
                    () -> new NotFoundException("Not Found with id : " + balancesDto.getId())
            );
            balances = balancesRepository.save(convert.convertToEntity(balancesDto,balancesOld));
        }
        else {
            balances = balancesRepository.save(convert.convertToEntity(balancesDto));
        }
        return convert.convertToDto(balances);
    }

    @Override
    public void delete(int id) {
        balancesRepository.deleteById(id);
    }

}

