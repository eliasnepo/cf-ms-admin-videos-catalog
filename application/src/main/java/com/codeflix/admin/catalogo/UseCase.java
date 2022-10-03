package com.codeflix.admin.catalogo;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN in);
}
