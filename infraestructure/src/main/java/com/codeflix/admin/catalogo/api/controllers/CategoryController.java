package com.codeflix.admin.catalogo.api.controllers;

import com.codeflix.admin.catalogo.api.CategoryAPI;
import com.codeflix.admin.catalogo.application.category.create.CreateCategoryInput;
import com.codeflix.admin.catalogo.application.category.create.CreateCategoryOutput;
import com.codeflix.admin.catalogo.application.category.create.CreateCategoryUseCase;
import com.codeflix.admin.catalogo.application.category.delete.DeleteCategoryUseCase;
import com.codeflix.admin.catalogo.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.codeflix.admin.catalogo.application.category.retrieve.list.ListCategoriesUseCase;
import com.codeflix.admin.catalogo.application.category.update.UpdateCategoryInput;
import com.codeflix.admin.catalogo.application.category.update.UpdateCategoryOutput;
import com.codeflix.admin.catalogo.application.category.update.UpdateCategoryUseCase;
import com.codeflix.admin.catalogo.category.models.CategoryListResponse;
import com.codeflix.admin.catalogo.category.models.CreateCategoryRequest;
import com.codeflix.admin.catalogo.category.models.GetCategoryResponse;
import com.codeflix.admin.catalogo.category.models.UpdateCategoryRequest;
import com.codeflix.admin.catalogo.category.presenters.CategoryApiPresenter;
import com.codeflix.admin.catalogo.domain.category.CategorySearchQuery;
import com.codeflix.admin.catalogo.domain.pagination.Pagination;
import com.codeflix.admin.catalogo.domain.validation.handler.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;

    public CategoryController(final CreateCategoryUseCase createCategoryUseCase, final GetCategoryByIdUseCase getCategoryByIdUseCase, final UpdateCategoryUseCase updateCategoryUseCase, final DeleteCategoryUseCase deleteCategoryUseCase, final ListCategoriesUseCase listCategoriesUseCase) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
        this.getCategoryByIdUseCase = getCategoryByIdUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
        this.listCategoriesUseCase = listCategoriesUseCase;
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryRequest request) {
        final var aCommand = CreateCategoryInput.with(
                request.name(),
                request.description(),
                request.active() != null ? request.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);

        return this.createCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<CategoryListResponse> listCategories(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return listCategoriesUseCase.execute(new CategorySearchQuery(page, perPage, search, sort, direction))
                .map(CategoryApiPresenter::present);
    }

    @Override
    public GetCategoryResponse getById(final String id) {
        return CategoryApiPresenter.present(this.getCategoryByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateCategoryRequest input) {
        final var aCommand = UpdateCategoryInput.with(
                id,
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateCategoryOutput, ResponseEntity<?>> onSuccess =
                ResponseEntity::ok;

        return this.updateCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public void deleteById(final String anId) {
        this.deleteCategoryUseCase.execute(anId);
    }
}