package com.bkap.utils.service.impl;

import com.bkap.convert.ServicePackageConvert;
import com.bkap.dto.ServicePackageDto;
import com.bkap.entities.ServicePackage;
import com.bkap.exception.DuplicateRecordException;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.ServicePackageRespository;
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
public class ServicePackageService implements IGeneralService<ServicePackageDto, BaseFilter> {

    @Autowired
    private ServicePackageRespository servicePackageRespository;

    @Autowired
    private ServicePackageConvert convert;

    @Override
    public List<ServicePackageDto> findAll() {
        return null;
    }

    @Override
    public Page<ServicePackageDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<ServicePackage> pageServicePackageEntitys = servicePackageRespository.findAll(new Specification<ServicePackage>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<ServicePackage> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
        return pageServicePackageEntitys.map(servicePackageEntity -> convert.convertToDto(servicePackageEntity));
    }

    @Override
    public ServicePackageDto findById(int id) {
        return convert.convertToDto(
                servicePackageRespository.findById(id).
                        orElseThrow(() -> new NotFoundException("Service Package Not Found with id : " + id)));
    }

    @Override
    public ServicePackageDto save(ServicePackageDto servicePackageDto) {
        ServicePackage servicePackage = new ServicePackage();
        ServicePackage servicePackageByTitle = servicePackageRespository.findByTitle(servicePackageDto.getTitle()).orElse (null);
        if (servicePackageDto.getId() != 0) {
            ServicePackage servicePackageOld = servicePackageRespository.findById(servicePackageDto.getId()).orElseThrow(
                    () -> new NotFoundException("Room Not Found with id : " + servicePackageDto.getId())

            );
            if(servicePackageByTitle != null) {
                if(servicePackageByTitle.getId() ==  servicePackageOld.getId()){
                    servicePackage = servicePackageRespository.save(convert.convertToEntity(servicePackageDto, servicePackageOld));
                }
                else {
                    throw new DuplicateRecordException( servicePackageByTitle.getTitle() + " does exist in the database");
                }
            }
            else {
                servicePackage = servicePackageRespository.save(convert.convertToEntity(servicePackageDto, servicePackageOld));
            }
        }
        else {
            if(servicePackageByTitle == null){
                servicePackage = servicePackageRespository.save(convert.convertToEntity(servicePackageDto));
            } else {
                throw  new DuplicateRecordException( servicePackageByTitle.getTitle() + " does exist in the database");
            }
        }
        return convert.convertToDto(servicePackage);
    }

    @Override
    public void delete(int id) {
        servicePackageRespository.deleteById(id);
    }
}
