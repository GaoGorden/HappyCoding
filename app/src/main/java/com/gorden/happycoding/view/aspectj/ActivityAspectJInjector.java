//package com.gorden.happycoding.view.aspectj;
//
//import android.util.Log;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//
//@Aspect
//public class ActivityAspectJInjector {
//
//    @Pointcut("execution(* androidx.appcompat.app.AppCompatActivity.setContentView(..))")
//    public void ActivitySetContViewPointcut() {
//
//    }
//
//    @Around("ActivitySetContViewPointcut()")
//    public void appCompatActivitySetContentView(final ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//        Object target = joinPoint.getTarget();
//        Log.e("aspectJx", "AppCompatActivity setContentView: target = " + target);
//        joinPoint.proceed();
//        long end = System.currentTimeMillis();
//        Log.e("aspectJx", "AppCompatActivity setContentView: time cost = " + (end - start));
//    }
//
//}
