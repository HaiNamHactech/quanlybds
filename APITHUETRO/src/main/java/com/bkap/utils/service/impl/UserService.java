package com.bkap.utils.service.impl;

import com.bkap.convert.UserConvert;
import com.bkap.dto.UserDto;
import com.bkap.entities.User;
import com.bkap.exception.DuplicateRecordException;
import com.bkap.exception.NotFoundException;
import com.bkap.filter.BaseFilter;
import com.bkap.repositories.UserRepository;
import com.bkap.utils.MyUser;
import com.bkap.utils.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService,IGeneralService<UserDto, BaseFilter> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConvert convert;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public Page<UserDto> paginations(BaseFilter filter) {
        Sort sort = Sort.unsorted();
        if(filter.getSort().length > 0){
            List<Sort.Order> orders = new ArrayList<>();
            Arrays.stream(filter.getSort()).forEach((x) -> {
                orders.add(new Sort.Order(x.getType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , x.getField()));
            });
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Page<User> pageUserEntitys = userRepository.findAll(new Specification<User>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
        return pageUserEntitys.map(user -> convert.convertToDto(user));
    }

    @Override
    public UserDto findById(int id) {
        return convert.convertToDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found with id : " + id)));
    }

    @Override
    public UserDto save(UserDto userDto) {
        if(userDto.getPass() != null) {
            userDto.setPass(passwordEncoder.encode(userDto.getPass()));
        }
        User user = new User();
        User userByName = userRepository.findByName(userDto.getName()).orElse (null);
        if (userDto.getId() != 0) {
            User userOld = userRepository.findById(userDto.getId()).orElseThrow(
                    () -> new NotFoundException("User Not Found with id : " + userDto.getId())

            );
            if(userByName != null) {
                if(userByName.getId() ==  userOld.getId()){
                    user = userRepository.save(convert.convertToEntity(userDto, userOld));
                }
                else {
                    throw new DuplicateRecordException( userByName.getName() + " does exist in the database");
                }
            }
            else {
                user = userRepository.save(convert.convertToEntity(userDto, userOld));
            }
        }
        else {
            if(userByName == null){
                user = userRepository.save(convert.convertToEntity(userDto));
            } else {
                throw  new DuplicateRecordException( userByName.getName() + " does exist in the database");
            }
        }
        return convert.convertToDto(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
    public Float getaccountBalance(int userId){
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            return null;
        }
        else {
            return userRepository.accountBalance(userId);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        User user = userRepository.findByName(firstName).orElseThrow(() -> new NotFoundException("User Not Found with name : "));
        if (user == null) {
            return null;
        }
        MyUser myUser = new MyUser(firstName, user.getPass(), true, true, true, true, user.getAuthorities());
        myUser.setFullName(firstName);
        myUser.setUser(user);
        return myUser;
    }

    public UserDetails loadUserById(int id) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return null;
        }
        MyUser myUser = new MyUser(user.get().getName(), user.get().getPass(), true, true, true, true,
                user.get().getAuthorities());
        myUser.setFullName(user.get().getName());
        myUser.setUser(user.get());
        return myUser;
    }
}
