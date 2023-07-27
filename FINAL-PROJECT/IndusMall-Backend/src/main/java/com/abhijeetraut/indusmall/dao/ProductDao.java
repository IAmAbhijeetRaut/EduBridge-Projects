package com.abhijeetraut.indusmall.dao;

import com.abhijeetraut.indusmall.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Abhijeet Raut on || Date : 19-06-2023 ||  Time : 12:49 pm.
 */
@Repository
public interface ProductDao extends CrudRepository<Product, Integer > {

    public List<Product> findAll(Pageable pageable);

    public List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(String key1, String key2, Pageable pageable);
}
