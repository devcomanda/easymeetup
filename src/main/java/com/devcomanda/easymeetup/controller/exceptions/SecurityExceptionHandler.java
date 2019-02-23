package com.devcomanda.easymeetup.controller.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@ControllerAdvice
public class SecurityExceptionHandler implements SecurityAdviceTrait {
}
