<#-- 校验器模板-->
package ${_package}.${_model?lower_case};

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 *  ${_model?cap_first} Validator.
 *  @author ZTransformer
 */
public class ${_model?cap_first}Validator extends Validator {
	
	protected void validate(Controller controller) {
		//默认生成数字和日期，其他请按需添加
		<#list _columns as x>
			<#if x.name=="id">
			<#else>
			<#if x.type == "4">			
		validateInteger("${_model}.${x.name}", Integer.MIN_VALUE, Integer.MAX_VALUE, "${x.name}Msg", "请输入数字！");
			</#if>			
			<#if x.type == "8">			
		validateDouble("${_model}.${x.name}", Double.MIN_VALUE, Double.MAX_VALUE, "${x.name}Msg", "请输入数字！");
			</#if>
			<#if x.type == "91">			
		validateDate("${_model}.${x.name}", "1970-12-08", "2099-12-31", "${x.name}Msg", "请输入日期！");
			</#if>
			</#if>
		</#list>		
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(${_model?cap_first}.class);		
		String actionKey = getActionKey();
		if (actionKey.equals("/${_model}/save")){
			controller.render("add.html");
		}else if (actionKey.equals("/${_model}/update")){
			controller.render("edit.html");
		}
	}
}
