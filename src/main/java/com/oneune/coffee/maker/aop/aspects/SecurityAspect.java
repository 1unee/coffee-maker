package com.oneune.coffee.maker.aop.aspects;

import com.oneune.coffee.maker.aop.annotations.Security;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
@Log4j2
public class SecurityAspect {

    @Value("${security.key.name}")
    private String securityKeyName;

    @Value("${security.key.value}")
    private String securityKeyValue;

    private final HttpServletRequest request;

    @Before("@annotation(security)")
    public void checkSecurityKey(JoinPoint joinPoint, Security security) throws Throwable {

        String securityKey = request.getParameter(securityKeyName);

        if (Objects.isNull(securityKey) || !securityKey.equals(securityKeyValue)) {
            throw new SecurityException("Key is not correct or missed in params!");
        }

        log.info("Security check passed for method: {}", joinPoint.getSignature().getName());
    }
}

