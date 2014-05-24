<#-- 修改对象页面 -->
${"\l"}#include "../common/_header.html"/>
${"\l"}#include "../common/_nav.html"/>
${"\l"}#include "../common/_mmenu.html"/>
${"\l"}#include "../common/_siderbar.html"/>
${"\l"}#include "../common/_premark.html"/>
	<h2>修改${_model?cap_first}</h2>
	<form class="form-horizontal" action="/${_model}/update" method="post">		
			${"\l"}#include "_form.html" />
	</form>
${"\l"}#include "../common/_mark.html"/>
${"\l"}#include "../common/_foot.html"/>