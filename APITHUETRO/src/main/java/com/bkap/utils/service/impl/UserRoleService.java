package com.bkap.utils.service.impl;

import com.bkap.convert.UserRoleConvert;
import com.bkap.dto.UserRoleDto;
import com.bkap.entities.UserRole;
import com.bkap.exception.DuplicateRecordException;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.UserRoleRepository;
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
public class UserRoleService implements IGeneralService<UserRoleDto, BaseFilter> {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRoleConvert convert;

    @Override
    public List<UserRoleDto> findAll() {
        return null;
    }

    @Override
    public Page<UserRoleDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<UserRole> pageUserRoleEntitys = userRoleRepository.findAll(new Specification<UserRole>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<UserRole> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                filter.getColumnFilters().forEach((key,value) -> {
                    if(key != null && !value.equals("")) {
                        if (key.equals("userId")) {
                            predicates.add(criteriaBuilder.equal(root.get("user").get("id"), value));
                        } else if (key.equals("groupRoleId")) {
                            predicates.add(criteriaBuilder.equal(root.get("groupRole").get("id"), value));
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
        return pageUserRoleEntitys.map(roleFunction -> convert.convertToDto(roleFunction));
    }

    @Override
    public UserRoleDto findById(int id) {
        return convert.convertToDto(userRoleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("user role Not Found with id : " + id)));
    }
    @Override
    public UserRoleDto save(UserRoleDto userRoleDto) {
        UserRole userRole = new UserRole();
        Object userRoleId = userRoleRepository.findByUserIdAndRoleId(userRoleDto.getUserId(),userRoleDto.getGroupRoleId());
        if (userRoleDto.getId() != 0) {
            UserRole userRoleOld = userRoleRepository.findById(userRoleDto.getId()).orElseThrow(
                    () -> new NotFoundException("Not Found with id : " + userRoleDto.getId())
            );
            if(userRoleId != null){
                if(Integer.parseInt(userRoleId.toString()) == userRoleOld.getId()){
                    userRole = userRoleRepository.save(convert.convertToEntity(userRoleDto, userRoleOld));
                }
                else {
                    throw new DuplicateRecordException(" userId and groupRoleId does exist in the database");
                }
            }
            else {
                userRole = userRoleRepository.save(convert.convertToEntity(userRoleDto, userRoleOld));
            }
        }
        else {
            if(userRoleId == null){
                userRole = userRoleRepository.save(convert.convertToEntity(userRoleDto));
            } else {
                throw  new DuplicateRecordException("userId and groupRoleId does exist in the database");
            }
        }
        return convert.convertToDto(userRole);
    }

    @Override
    public void delete(int id) {
        userRoleRepository.deleteById(id);

    }
}
