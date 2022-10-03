package com.codeflix.admin.catalogo.category.update;

public record UpdateCategoryInput(
        String id,
        String name,
        String description,
        boolean isActive
) {

    public static UpdateCategoryInput with(
            final String anId,
            final String aName,
            final String aDescription,
            final boolean isActive
    ) {
        return new UpdateCategoryInput(anId, aName, aDescription, isActive);
    }
}