package com.yccz.jdbcencapsulation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.lang.model.element.Element;

/**
 * �������ݿ��ֶ����� java ʵ�����ֶε�ӳ��
 * @author 2017/09/13 DuanJiaNing
 *
 */
@Target(value={FIELD,TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Mapping {
	String value() default "";
}
