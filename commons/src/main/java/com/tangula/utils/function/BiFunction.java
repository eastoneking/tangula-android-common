package com.tangula.utils.function;

public interface BiFunction<T, U, V> {
    V invoke(T t, U u);
}

