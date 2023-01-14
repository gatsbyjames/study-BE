package com.name.blog.core.security;

public interface AuthToken<T> {
    boolean validate();
    T getData();
}