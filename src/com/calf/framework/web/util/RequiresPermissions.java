package com.calf.framework.web.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
/**
 * 权限annotation
 *
 */
public @interface RequiresPermissions {
	/**
	 * 
	 * @return
	 */
	public String value() default "";
	
	public boolean requiresUser() default false;
}
