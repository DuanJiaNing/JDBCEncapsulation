package com.yccz.jdbcencapsulation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


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
