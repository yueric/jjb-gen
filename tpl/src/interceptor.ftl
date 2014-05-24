<#-- 拦截器模板-->
package ${_package}.${_model?lower_case};

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

/**
 *  ${_model?cap_first}Interceptor
 *  @author ZTransformer
 */
public class ${_model?cap_first}Interceptor implements Interceptor {
	
	public void intercept(ActionInvocation ai) {
		ai.getController().setAttr("_MODULE", "${_model}");
		ai.invoke();
	}
}
