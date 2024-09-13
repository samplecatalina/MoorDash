package com.moordash.aspect;

import com.moordash.annotation.AutoFill;
import com.moordash.constant.AutoFillConstant;
import com.moordash.context.BaseContext;
import com.moordash.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * Custom aspect to implement automatic filling of public fields
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * POINTCUT
     * Pointcuts typically define the methods that will be intercepted by the advice;
     * Pointcuts depend on a method that does not have parameters/body/return value;
     *
     * Here we will intercept all methods in the com.moordash.mapper package that have the AutoFill annotation
     */
    @Pointcut("execution(* com.moordash.mapper.*.*(..)) && @annotation(com.moordash.annotation.AutoFill)")
    public void autoFillPointCut() {

    }

    /**
     * ADVICE
     * BEFORE ADVICE here is used to fill in the public fields of the entity object in the advice.
     * "@Before("autoFillPointCut()")" is used to bind the advice to the pointcut.
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {

        log.info("Starting automatic filling of public fields");

        // Get the database operation type on the current intercepted method

        // Get the method signature object
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // Get the annotation object on the method
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);

        // Get the database operation type
        OperationType operationType = autoFill.value();

        // Get the parameters of the current intercepted method: entity object
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        // Rule in this project: entity object must be the first parameter
        Object entity = args[0];

        // Prepare the data to be filled in
        // Get the current time and current user ID
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        // Assign values to the corresponding properties through reflection according to the current operation type
        if (operationType == OperationType.INSERT) {
            // Assign values to 4 public fields
            try {
                // Get the set methods of the entity object
                Method setCreateTime =
                        entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser =
                        entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime =
                        entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser =
                        entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // Call the entity object's set methods to assign values
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);

            } catch (Exception e) { // Compulsory exception handling
                throw new RuntimeException(e);
            }

        } else if (operationType == operationType.UPDATE) {
            // Assign values to 2 public fields
            try {
                Method setUpdateTime =
                        entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser =
                        entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // Assign values through reflection
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
