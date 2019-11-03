package com.cueo.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.slf4j.LoggerFactory

/**
 * @author cueo
 */
class LoggingAspect {

    private var logger = LoggerFactory.getLogger(LoggingAspect::class.java)

    @Around("annotation(com.cueo.annotation.LogExecutionTime)")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint) : Any {
        // Calculate execution time for the method
        val startTime = System.currentTimeMillis()
        val proceed = joinPoint.proceed()
        val executionTime = System.currentTimeMillis() - startTime

        // Override logger so that class name is printed of the calling method instead of this class
        val clazz = joinPoint.signature.declaringType
        logger = LoggerFactory.getLogger(clazz)

        // Log the execution time of the method
        logger.info("Total execution time in milliseconds: time=$executionTime")

        return proceed
    }
}
