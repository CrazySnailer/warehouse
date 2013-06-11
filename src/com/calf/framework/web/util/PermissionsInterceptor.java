/**
 * 
 */
package com.calf.framework.web.util;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.util.Constants;
import com.calf.framework.vo.AdminUserInfo;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 权限拦截器
 * 
 */
public class PermissionsInterceptor extends AbstractInterceptor {
	/**
	 * 授权失败
	 */
	private static final String PERMISSION_DENIED = "global_permission_error";
	/**
	 * 重新登录
	 */
	private static final String LOGIN = "login";
	
	/**
	 * 资源命名方法
	 * RES_ID    M_ 菜单      R_ 读权限      W_ 写权限
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//if(1==1){return invocation.invoke();}
		Method methods[] = invocation.getProxy().getAction().getClass().getMethods();
		String thisMethod = invocation.getProxy().getMethod();
		for (int i = 0, len = methods.length; i < len; i++) {
			Method method = methods[i];
			if(thisMethod.equals(method.getName())){
				boolean hasAnnotation = method.isAnnotationPresent(RequiresPermissions.class);
				if(hasAnnotation){
					RequiresPermissions annotation = method.getAnnotation(RequiresPermissions.class);
					boolean requiresUser = annotation.requiresUser();//是否需要检验是否的登录
					String resId = annotation.value();//判断是否有此权限存在
					if(requiresUser){//此处需要增加不需要判断登录处理，同时需要判断写数据权限,查看数据权限只判断到个人，高级别的权限不做判断
						Map session = invocation.getInvocationContext().getSession();
						AdminUserInfo userInfo = (AdminUserInfo)session.get(Constants.ADMIN_SESSION_USER_INFO);
						if(userInfo!=null){
							if(StringUtils.isNotBlank(resId)){//不为空则需要做权限验证								
								if(!userInfo.findResources(resId)){
									return PERMISSION_DENIED;//权限不足
								}
							}
						}else{
							return LOGIN;
						}
					}
				}
			}
		}

		return invocation.invoke();
	}

}
