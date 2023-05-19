package com.bkap.utils.service.impl;

import com.bkap.convert.ContactConvert;
import com.bkap.dto.ContactDto;
import com.bkap.entities.Contact;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
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
public class ContactService implements IGeneralService<ContactDto, BaseFilter> {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactConvert convert;

    @Override
    public List<ContactDto> findAll() {
        return null;
    }

    @Override
    public Page<ContactDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<Contact> pageContacts = contactRepository.findAll(new Specification<Contact>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
        return pageContacts.map(pageContact -> convert.convertToDto(pageContact));
    }

    @Override
    public ContactDto findById(int id) {
        return convert.convertToDto(
                contactRepository.findById(id).
                        orElseThrow(() -> new NotFoundException("Contact Not Found with id : " + id)));
    }

    @Override
    public ContactDto save(ContactDto contactDto) {
        Contact contact = new Contact();
        if (contactDto.getId() != 0) {
            Contact contactOld = contactRepository.findById(contactDto.getId()).orElseThrow(
                    () -> new NotFoundException("Not Found with id : " + contactDto.getId())
            );
            contact = contactRepository.save(convert.convertToEntity(contactDto, contactOld));
        }
        else {
            contact = contactRepository.save(convert.convertToEntity(contactDto));
        }
        return convert.convertToDto(contact);
    }

    @Override
    public void delete(int id) {
        contactRepository.deleteById(id);
    }
}
