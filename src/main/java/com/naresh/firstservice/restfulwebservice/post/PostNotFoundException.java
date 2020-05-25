package com.naresh.firstservice.restfulwebservice.post;

import com.naresh.firstservice.restfulwebservice.exception.ExceptionResponse;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message){ super(message);}
}
