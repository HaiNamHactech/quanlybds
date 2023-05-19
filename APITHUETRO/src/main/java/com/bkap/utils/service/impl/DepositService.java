package com.bkap.utils.service.impl;
import com.bkap.convert.DepositConvert;
import com.bkap.dto.DepositDto;
import com.bkap.entities.Deposit;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.DepositRepository;
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
public class DepositService implements IGeneralService<DepositDto, BaseFilter> {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private DepositConvert convert;

    @Override
    public List<DepositDto> findAll() {
        return null;
    }

    @Override
    public Page<DepositDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<Deposit> pageDeposit = depositRepository.findAll(new Specification<Deposit>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Deposit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                filter.getColumnFilters().forEach((key,value) -> {
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
                });
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
        return pageDeposit.map(page -> convert.convertToDto(page));
    }

    @Override
    public DepositDto findById(int id) {
        return convert.convertToDto(
                depositRepository.findById(id).
                        orElseThrow(() -> new NotFoundException("Deposit Not Found with id : " + id)));
    }

    @Override
    public DepositDto save(DepositDto depositDto) {
        Deposit deposit = new Deposit();
        if (depositDto.getId() != 0) {
            Deposit depositOld = depositRepository.findById(depositDto.getId()).orElseThrow(
                    () -> new NotFoundException("Not Found with id : " + depositDto.getId())
            );
            deposit = depositRepository.save(convert.convertToEntity(depositDto,depositOld));
        }
        else {
            deposit = depositRepository.save(convert.convertToEntity(depositDto));
        }
        return convert.convertToDto(deposit);
    }

    @Override
    public void delete(int id) {
        depositRepository.deleteById(id);
    }

}
