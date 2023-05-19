package com.bkap.utils.service.impl;

import com.bkap.convert.GroupRoleConvert;
import com.bkap.dto.GroupRoleDto;
import com.bkap.entities.GroupRole;
import com.bkap.exception.DuplicateRecordException;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.GroupRoleRepository;
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
public class GroupRoleService implements IGeneralService<GroupRoleDto, BaseFilter> {

    @Autowired
    private GroupRoleRepository groupRoleRepository;

    @Autowired
    private GroupRoleConvert convert;

    @Override
    public List<GroupRoleDto> findAll() {
        return null;
    }

    @Override
    public Page<GroupRoleDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<GroupRole> pageGroupRoleEntitys = groupRoleRepository.findAll(new Specification<GroupRole>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<GroupRole> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
        return pageGroupRoleEntitys.map(groupRole -> convert.convertToDto(groupRole));
    }

    @Override
    public GroupRoleDto findById(int id) {
        return convert.convertToDto(groupRoleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role Not Found with id : " + id)));
    }

    @Override
    public GroupRoleDto save(GroupRoleDto groupRoleDto) {
        GroupRole groupRole = new GroupRole();
        GroupRole groupRoleByName = groupRoleRepository.findByRoleName(groupRoleDto.getRoleName()).orElse (null);
        if (groupRoleDto.getId() != 0) {
            GroupRole groupRoleOld = groupRoleRepository.findById(groupRoleDto.getId()).orElseThrow(
                    () -> new NotFoundException("Role Not Found with id : " + groupRoleDto.getId())
            );
            if(groupRoleByName != null) {
                if(groupRoleByName.getId() ==  groupRoleOld.getId()){
                    groupRole = groupRoleRepository.save(convert.convertToEntity(groupRoleDto, groupRoleOld));
                }
                else {
                    throw new DuplicateRecordException( groupRoleByName.getRoleName()+ " does exist in the database");
                }
            }
            else {
                groupRole = groupRoleRepository.save(convert.convertToEntity(groupRoleDto, groupRoleOld));
            }
        }
        else {
            if(groupRoleByName == null){
                groupRole = groupRoleRepository.save(convert.convertToEntity(groupRoleDto));
            } else {
                throw  new DuplicateRecordException( groupRoleByName.getRoleName() + " does exist in the database");
            }
        }
        return convert.convertToDto(groupRole);
    }

    @Override
    public void delete(int id) {
        groupRoleRepository.deleteById(id);
    }
}
