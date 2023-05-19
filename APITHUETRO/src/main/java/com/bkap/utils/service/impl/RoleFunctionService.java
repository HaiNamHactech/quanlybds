package com.bkap.utils.service.impl;

import com.bkap.convert.RoleFunctionConvert;
import com.bkap.dto.RoleFunctionDto;
import com.bkap.entities.RoleFunction;
import com.bkap.exception.DuplicateRecordException;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.RoleFunctionRepository;
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
public class RoleFunctionService implements IGeneralService<RoleFunctionDto, BaseFilter> {

    @Autowired
    private RoleFunctionRepository roleFunctionRepository;

    @Autowired
    private RoleFunctionConvert convert;

    @Override
    public List<RoleFunctionDto> findAll() {
        return null;
    }

    @Override
    public Page<RoleFunctionDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<RoleFunction> pageRoleFunctionEntitys = roleFunctionRepository.findAll(new Specification<RoleFunction>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<RoleFunction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                filter.getColumnFilters().forEach((key,value) -> {
                    if(key != null && !value.equals("")) {
                        if (key.equals("groupRoleId")) {
                            predicates.add(criteriaBuilder.equal(root.get("groupRole").get("id"), value));
                        } else if (key.equals("functionId")) {
                            predicates.add(criteriaBuilder.equal(root.get("function").get("id"), value));
                        } else {
                            if (key.endsWith("!ext")) {
                                String ekey = key.replace("!ext", "");
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
        return pageRoleFunctionEntitys.map(roleFunction -> convert.convertToDto(roleFunction));
    }

    @Override
    public RoleFunctionDto findById(int id) {
        return convert.convertToDto(roleFunctionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("RoleFunction Not Found with id : " + id)));
    }

    @Override
    public RoleFunctionDto save(RoleFunctionDto roleFunctiondto) {
        RoleFunction roleFunction = new RoleFunction();
        Object roleFunctionId = roleFunctionRepository.findByFunctionIdAndGroupRoleId(roleFunctiondto.getGroupRoleId(),roleFunctiondto.getFunctionId());
        if (roleFunctiondto.getId() != 0) {
            RoleFunction roleFunctionOld = roleFunctionRepository.findById(roleFunctiondto.getId()).orElseThrow(
                    () -> new NotFoundException("Not Found with id : " + roleFunctiondto.getId())
            );
            if(roleFunctionId != null){
                if(Integer.parseInt(roleFunctionId.toString()) == roleFunctionOld.getId()){
                    roleFunction = roleFunctionRepository.save(convert.convertToEntity(roleFunctiondto, roleFunctionOld));
                }
                else {
                    throw new DuplicateRecordException(" groupRoleId and functionId does exist in the database");
                }
            }
            else {
                roleFunction = roleFunctionRepository.save(convert.convertToEntity(roleFunctiondto, roleFunctionOld));
            }
        }
        else {
            if(roleFunctionId == null){
                roleFunction = roleFunctionRepository.save(convert.convertToEntity(roleFunctiondto));
            } else {
                throw  new DuplicateRecordException("groupRoleId and functionId does exist in the database");
            }
        }
        return convert.convertToDto(roleFunction);
    }

    @Override
    public void delete(int id) {
        roleFunctionRepository.deleteById(id);
    }
}
