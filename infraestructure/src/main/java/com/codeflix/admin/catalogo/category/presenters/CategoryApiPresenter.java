package com.codeflix.admin.catalogo.category.presenters;

import com.codeflix.admin.catalogo.application.category.retrieve.get.CategoryOutput;
import com.codeflix.admin.catalogo.application.category.retrieve.list.CategoryListOutput;
import com.codeflix.admin.catalogo.category.models.CategoryListResponse;
import com.codeflix.admin.catalogo.category.models.GetCategoryResponse;

public interface CategoryApiPresenter {

    static GetCategoryResponse present(final CategoryOutput output) {
        return new GetCategoryResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static CategoryListResponse present(final CategoryListOutput output) {
        return new CategoryListResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}