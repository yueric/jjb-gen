<#-- model模板-->
package ${_package}.${_model?lower_case};

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 *  ${_model?cap_first} model.
 *  @author ZTransformer
 */
@SuppressWarnings("serial")
public class ${_model?cap_first} extends Model<${_model?cap_first}> {
	public static final ${_model?cap_first} dao = new ${_model?cap_first}();
	
	public static Page<${_model?cap_first}> getQuery(int page){
		return dao.paginate(page, 10, "select *", "from ${_name} order by id asc");		
	}
}