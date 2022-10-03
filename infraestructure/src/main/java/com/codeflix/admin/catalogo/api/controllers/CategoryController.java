package com.codeflix.admin.catalogo.api.controllers;

import com.codeflix.admin.catalogo.api.CategoryAPI;
import com.codeflix.admin.catalogo.domain.pagination.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements CategoryAPI {

    @Override
    public ResponseEntity<?> createCategory() {
        return null;
    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, String sort, String direction) {
        return null;
    }
}