package com.bkap.utils.service.impl;

import com.bkap.convert.BlogConvert;
import com.bkap.convert.ContactConvert;
import com.bkap.dto.BlogDto;
import com.bkap.entities.Blog;
import com.bkap.entities.Contact;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.BlogRepository;
import com.bkap.repositories.ContactRepository;
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
public class BlogService implements IGeneralService<BlogDto, BaseFilter> {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogConvert convert;

    @Override
    public List<BlogDto> findAll() {
        return null;
    }

    @Override
    public Page<BlogDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<Blog> pageBlogs = blogRepository.findAll(new Specification<Blog>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
        return pageBlogs.map(page -> convert.convertToDto(page));
    }

    @Override
    public BlogDto findById(int id) {
        return convert.convertToDto(
                blogRepository.findById(id).
                        orElseThrow(() -> new NotFoundException("Blog Not Found with id : " + id)));
    }

    @Override
    public BlogDto save(BlogDto blogDto) {
        System.out.println(blogDto);
        Blog blog = new Blog();
        if (blogDto.getId() != 0) {
            Blog blogOld = blogRepository.findById(blogDto.getId()).orElseThrow(
                    () -> new NotFoundException("Not Found with id : " + blogDto.getId())
            );
            blog = blogRepository.save(convert.convertToEntity(blogDto,blogOld));
        }
        else {
            blog = blogRepository.save(convert.convertToEntity(blogDto));
        }
        return convert.convertToDto(blog);
    }

    @Override
    public void delete(int id) {
        blogRepository.deleteById(id);
    }
}
