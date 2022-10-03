package com.codeflix.admin.catalogo.category.create;

import com.codeflix.admin.catalogo.UseCase;
import com.codeflix.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
        extends UseCase<CreateCategoryInput, Either<Notification, CreateCategoryOutput>> {
}