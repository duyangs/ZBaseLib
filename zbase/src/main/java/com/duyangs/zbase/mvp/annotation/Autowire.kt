package com.duyangs.zbase.mvp.annotation

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

/**
 * 创建者：梁建军
 * 创建日期： 2017/5/26
 * 创建时间： 11:53
 * Autowired
 * 版本：1.0
 * 说明：自动装配
 * 注解保持到运行时
 * 只能放到变量上
 *
 *
 * 用法：
 * 第一种用法：
 * 直接在变量上规定的实现类
 * //@Autowired(LoginModelImpl.class)
 * //private LoginModel loginModel;
 *
 *
 *
 *
 *
 *
 * 第二种用法
 * 接口自己规定实现类
 * //@Autowired()
 * //private LoginModel loginModel;
 * 在接口类上加这个注解
 * //@Autowired(LoginModelImpl.class)
 * //public interface LoginModel {
 * //...
 * //}
 *
 *
 * 当两种一起使用时候，以第一种为主
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS, AnnotationTarget.FILE)
internal annotation class Autowire(
        /**
         * 自动装载到指定变量
         * 当这个属性的值是一个 注解的Class 时候，将调用@[com.duyangs.zbase.mvp.MvpAutowire.AnnotationAutowire.newAnnotation]这个方法
         *
         * @return 要装载的类
         */
        val value: KClass<*> = Any::class)
