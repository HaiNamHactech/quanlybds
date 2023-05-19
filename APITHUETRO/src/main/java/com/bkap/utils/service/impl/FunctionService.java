package com.bkap.utils.service.impl;

import com.bkap.convert.FunctionConvert;
import com.bkap.dto.FunctionDto;
import com.bkap.entities.Function;
import com.bkap.entities.User;
import com.bkap.exception.DuplicateRecordException;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.FunctionRepository;
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
public class FunctionService implements IGeneralService<FunctionDto, BaseFilter> {

    @Autowired
    private FunctionRepository functionRepository;

    @Autowired
    private FunctionConvert convert;

    @Override
    public List<FunctionDto> findAll() {
        return null;
    }

    @Override
    public Page<FunctionDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<Function> pageFunctionEntitys = functionRepository.findAll(new Specification<Function>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Function> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                filter.getColumnFilters().forEach((key,value) -> {
                    if(key != null && !value.equals("")) {
                        if (key.endsWith("!ext")) {
                            String ekey = key.replace("!ext", "");
                            predicates.add(criteriaBuilder.equal(root.get(ekey), value));
                        } else {
                            predicates.add(criteriaBuilder.like(root.get(key).as(String.class), "%" + value + "%"));
                        }
                    }
                });
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
        return pageFunctionEntitys.map(function -> convert.convertToDto(function));
    }

    @Override
    public FunctionDto findById(int id) {
        return  convert.convertToDto(
                functionRepository.findById(id).
                        orElseThrow(() -> new NotFoundException("Function Not Found with id : " + id)));
    }

    @Override
    public FunctionDto save(FunctionDto functionDto) {
        Function function = new Function();
        Function functionByName = functionRepository.findByFunctionName(functionDto.getFunctionName()).orElse (null);
        if (functionDto.getId() != 0) {
            Function functionOld = functionRepository.findById(functionDto.getId()).orElseThrow(
                    () -> new NotFoundException("Function Not Found with id : " + functionDto.getId())
            );
            if(functionByName != null) {
                if(functionByName.getId() ==  functionOld.getId()){
                    function = functionRepository.save(convert.convertToEntity(functionDto, functionOld));
                }
                else {
                    throw new DuplicateRecordException( functionByName.getFunctionName() + " does exist in the database");
                }
            }
            else {
                function = functionRepository.save(convert.convertToEntity(functionDto, functionOld));
            }
        }
        else {
            if(functionByName == null){
                function = functionRepository.save(convert.convertToEntity(functionDto));
            } else {
                throw  new DuplicateRecordException( functionByName.getFunctionName() + " does exist in the database");
            }
        }
        return convert.convertToDto(function);
    }

    @Override
    public void delete(int id) {
        functionRepository.deleteById(id);

    }
}
