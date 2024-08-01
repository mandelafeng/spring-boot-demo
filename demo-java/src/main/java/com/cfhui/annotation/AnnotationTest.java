package com.cfhui.annotation;

import java.lang.annotation.Annotation;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2024/8/1 16:35
 */
@MyAnnotation1("source")
@MyAnnotation2("runtime")
@MyAnnotation3("class")
public class AnnotationTest {

    public static void main(String[] args) {
        Annotation[] annotations = AnnotationTest.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType().getName());
        }
    }

}
