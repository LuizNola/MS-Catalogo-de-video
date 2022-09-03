package com.admin.catalogo.domain.pagination;

import java.util.List;

public record Pagination<T>(
        int CurrentPage,
        int perPage,
        long total,
        List<T> items) {

}
