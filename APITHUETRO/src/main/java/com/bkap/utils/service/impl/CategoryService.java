package com.bkap.utils.service.impl;

import com.bkap.convert.CategoryConvert;
import com.bkap.dto.CategoryDto;
import com.bkap.entities.Category;
import com.bkap.exception.DuplicateRecordException;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;

import com.bkap.repositories.CategoryRepository;
import com.bkap.utils.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryService implements IGeneralService<CategoryDto, BaseFilter> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConvert convert;

    @Override
    public List<CategoryDto> findAll() {
        List<CategoryDto> listCategoryDto = categoryRepository.findAll()
                .stream().map(cate -> convert.convertToDto(cate))
                .collect(Collectors.toList());
        return listCategoryDto;
    }

    @Override
    public Page<CategoryDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Order(x.getType().equals("asc") ? Direction.ASC : Direction.DESC  , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = null;
        if(filter.getPageSize() == 0){
             pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
        }
        else {
            pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        }
        Page<Category> pageCategoryEntitys = categoryRepository.findAll(new Specification<Category>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
        return pageCategoryEntitys.map(categoryEntity -> convert.convertToDto(categoryEntity));
    }

    @Override
    public CategoryDto findById(int id) {
        return convert.convertToDto(
                categoryRepository.findById(id).
                        orElseThrow(() -> new NotFoundException("Category Not Found with id : " + id)));
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = new Category();
        Category categoryByName = categoryRepository.findByName(categoryDto.getName()).orElse (null);
        if (categoryDto.getId() != 0) {
            Category categoryOld = categoryRepository.findById(categoryDto.getId()).orElseThrow(
                    () -> new NotFoundException("Category Not Found with id : " + categoryDto.getId())

            );
            if(categoryByName != null) {
                if(categoryByName.getId() ==  categoryOld.getId()){
                    category = categoryRepository.save(convert.convertToEntity(categoryDto, categoryOld));
                }
                else {
                    throw new DuplicateRecordException( categoryByName.getName() + " does exist in the database");
                }
            }
            else {
                category = categoryRepository.save(convert.convertToEntity(categoryDto, categoryOld));
            }
        }
        else {
            if(categoryByName == null){
                category = categoryRepository.save(convert.convertToEntity(categoryDto));
            } else {
                throw  new DuplicateRecordException( categoryByName.getName() + " does exist in the database");
            }
        }
        return convert.convertToDto(category);
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }
}
