<#-- 控制器模板-->
package ${_package}.${_model?lower_case};

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 *  ${_model?cap_first}Controller
 *  @author ZTransformer
 * 
 */
@Before(${_model?cap_first}Interceptor.class)
public class ${_model?cap_first}Controller extends Controller {
	public void index() {
		setAttr("${_model}Page", ${_model?cap_first}.getQuery(getParaToInt(0, 1)));
		render("list.html");
	}
	
	public void add() {
		render("add.html");
	}
	@Before(${_model?cap_first}Validator.class)
	public void save() {
		getModel(${_model?cap_first}.class).save();
		redirect("/${_model}");
	}
	
	public void edit() {
		setAttr("${_model}", ${_model?cap_first}.dao.findById(getParaToInt()));
	}
	
	@Before(${_model?cap_first}Validator.class)
	public void update() {
		getModel(${_model?cap_first}.class).update();
		redirect("/${_model}");
	}
	
	public void delete() {
		${_model?cap_first}.dao.deleteById(getParaToInt());
		redirect("/${_model}");
	}
}