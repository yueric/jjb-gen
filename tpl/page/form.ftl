<#-- 表单内容 -->
<fieldset>
	<legend>${_model?cap_first}维护</legend>
	<input type="hidden" name="${_model}.id" value="${r"$"}{(${_model}.id)!}" />
	<#list _columns as x>
		<#if x.name != "id">
			<#if x.type == "91">
		<div class="control-group">
	      <label class="control-label" for="${_model}.${x.name}">${x.comment}</label>
	      <div class="controls">
	      	<div class="input-append datepicker date" data-date="2014-02-10" data-date-format="yyyy-mm-dd">
	      	<input class="span6" type="text"  value="2014-02-10"  id="${x.name}" name="${_model}.${x.name}" /><span class="add-on"><i class="icon-calendar"></i></span>${r"$"}{${x.name}Msg!}
	      	</div>
	      </div>
	 	</div>
	 	<!--ready方法保持一个，如果有多个请合并-->
	 	 <script type="text/javascript">
			$(document).ready(function() {
			    $('.datepicker').datepicker();
			});
		</script>	
			<#else>
		<div class="control-group">
	      <label class="control-label" for="${_model}.${x.name}">${x.comment}</label>
	      <div class="controls">
	        <input type="text" class="input-xlarge" <#if x.name == "ludtime" || x.name=="ludor"><#else>name="${_model}.${x.name}"</#if> value="${r"$"}{(${_model}.${x.name})!}" <#if x.name == "ludtime" || x.name=="ludor"> readonly <#else><#if x.nullable == "0"> required </#if></#if>  /><#if x.type=="4" || x.type=="8" >${r"$"}{${x.name}Msg!}</#if>        
	      </div>
	 	</div>
 			</#if>		
		</#if>	 	
 	</#list>
 	<div class="control-group">
      <div class="controls">
        <button class="btn btn-success">提交</button> 
        <a href="/${_model}"><button class="btn btn-success">返回</button></a> 
      </div>
    </div>
</fieldset>