package com.bkap.utils.service.impl;


import com.bkap.convert.FavouriteConvert;
import com.bkap.dto.FavouriteDto;
import com.bkap.entities.Favourite;
import com.bkap.exception.DuplicateRecordException;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.FavouriteRepository;
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
public class FavouriteService  implements IGeneralService<FavouriteDto, BaseFilter>  {

    @Autowired
    private FavouriteConvert convert;

    @Autowired
    private FavouriteRepository favouriteRepository;


    @Override
    public List<FavouriteDto> findAll() {
        return null;
    }

    @Override
    public Page<FavouriteDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<Favourite> pageFavouriteEntitys = favouriteRepository.findAll(new Specification<Favourite>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Favourite> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                filter.getColumnFilters().forEach((key,value) -> {
                    if(key != null && !value.equals("")) {
                        if (key.equals("userId")) {
                            predicates.add(criteriaBuilder.equal(root.get("user").get("id"), value));
                        } else if (key.equals("roomId")) {
                            predicates.add(criteriaBuilder.equal(root.get("room").get("id"), value));
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
        return pageFavouriteEntitys.map(favourite -> convert.convertToDto(favourite));
    }

    @Override
    public FavouriteDto findById(int id) {
        return convert.convertToDto(
                favouriteRepository.findById(id).
                        orElseThrow(() -> new NotFoundException("Favourite Not Found with id : " + id)));
    }

    @Override
    public FavouriteDto save(FavouriteDto favouriteDto) {
        Favourite favourite = new Favourite();
        Object favouriteId = favouriteRepository.findByUserIdAndRoomId(favouriteDto.getRoomId(),favouriteDto.getUserId());
        System.out.println(favouriteId);
        if (favouriteDto.getId() != 0) {
            Favourite favouriteOld = favouriteRepository.findById(favouriteDto.getId()).orElseThrow(
                    () -> new NotFoundException("Not Found with id : " + favouriteDto.getId())
            );
            if(favouriteId != null){
                if(Integer.parseInt(favouriteId.toString()) == favouriteOld.getId()){
                    favourite = favouriteRepository.save(convert.convertToEntity(favouriteDto, favouriteOld));
                }
                else {
                    throw new DuplicateRecordException(" userId and roomId does exist in the database");
                }
            }
            else {
                favourite = favouriteRepository.save(convert.convertToEntity(favouriteDto, favouriteOld));
            }
        }
        else {
            if(favouriteId == null){
                favourite = favouriteRepository.save(convert.convertToEntity(favouriteDto));
            } else {
                throw  new DuplicateRecordException("userId and roomId does exist in the database");
            }
        }
        return convert.convertToDto(favourite);
    }

    @Override
    public void delete(int id) {
        favouriteRepository.deleteById(id);
    }
}
