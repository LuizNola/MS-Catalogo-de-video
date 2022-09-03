package com.admin.catalogo.domain.category;

import com.admin.catalogo.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category aCategory);
    void deleteById(CategoryId anId);
    Optional<Category> findById(CategoryId anId);
    Optional<Category> update(Category aCategory);
    Pagination<Category> findAll(CategorySearchQuery aQuery);



}
