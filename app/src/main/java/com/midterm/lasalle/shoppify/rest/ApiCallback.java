package com.midterm.lasalle.shoppify.rest;

import org.springframework.http.HttpStatus;
import java.util.Set;

public interface ApiCallback {

    void postResult(HttpStatus response, String description);
    void getResult(HttpStatus response, Set<Object> data);
}
