package com.tangula.utils.function;

public interface Function<T, U> {
    U invoke(T t);
}
