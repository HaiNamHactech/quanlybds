package com.bkap.utils.service.impl;

import com.bkap.convert.InstructConvert;
import com.bkap.dto.InstructDto;
import com.bkap.entities.Instruct;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.InstructRepository;
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
public class InstructService implements IGeneralService<InstructDto, BaseFilter> {

    @Autowired
    private InstructConvert convert;

    @Autowired
    private InstructRepository instructRepository;

    @Override
    public List<InstructDto> findAll() {
        return null;
    }

    @Override
    public Page<InstructDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<Instruct> pageInstructs = instructRepository.findAll(new Specification<Instruct>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Instruct> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
        return pageInstructs.map(pageInstruct -> convert.convertToDto(pageInstruct));
    }

    @Override
    public InstructDto findById(int id) {
        return convert.convertToDto(
                instructRepository.findById(id).
                        orElseThrow(() -> new NotFoundException("Instruct Not Found with id : " + id)));
    }

    @Override
    public InstructDto save(InstructDto instructDto) {
            Instruct instruct = new Instruct();
        if (instructDto.getId() != 0) {
            Instruct instructOld = instructRepository.findById(instructDto.getId()).orElseThrow(
                    () -> new NotFoundException("Not Found with id : " + instructDto.getId())
            );
            instruct = instructRepository.save(convert.convertToEntity(instructDto, instructOld));
        }
        else {
            instruct = instructRepository.save(convert.convertToEntity(instructDto));
        }
        return convert.convertToDto(instruct);
    }

    @Override
    public void delete(int id) {
        instructRepository.deleteById(id);
    }
}
