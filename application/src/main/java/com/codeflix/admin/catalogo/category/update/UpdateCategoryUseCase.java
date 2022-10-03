package com.codeflix.admin.catalogo.category.update;

import com.codeflix.admin.catalogo.UseCase;
import com.codeflix.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
        extends UseCase<UpdateCategoryInput, Either<Notification, UpdateCategoryOutput>> {
}