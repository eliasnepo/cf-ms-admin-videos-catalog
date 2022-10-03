package com.codeflix.admin.catalogo.category.create;

public record CreateCategoryInput(
        String name,
        String description,
        boolean isActive
) {

    public static CreateCategoryInput with(
            final String aName,
            final String aDescription,
            final boolean isActive
    ) {
        return new CreateCategoryInput(aName, aDescription, isActive);
    }
}