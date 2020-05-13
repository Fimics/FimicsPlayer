package com.mic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

//只能加在类上面
@Target(ElementType.TYPE)
public @interface ActivityDestination {
    String pageUrl();

    //是否需要登录
    boolean needLogin() default false;

    //是否为开始页面
    boolean asStarter() default false;
}
