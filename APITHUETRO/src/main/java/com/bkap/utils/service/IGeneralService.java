package com.bkap.utils.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IGeneralService<T,F> {
    List<T> findAll();

    Page<T>  paginations(F filter);

    T findById(int id);

    T save(T dto);

    void delete(int id);
}
