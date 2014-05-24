<#--  生成jfinal其他相关需要的相关配置 -->
1.Route配置，添加到configRoute方法中
me.add("/${_model}", ${_name?cap_first}Controller.class);

2.ormapping配置，添加到configPlugin方法中
arp.addMapping("${_model}", ${_name?cap_first}.class);

2.菜单配置，添加于common/_sidebar.html中，请修改对应的中文注解
<li class="${"\l"}#if _MODULE?exists && _MODULE=="${_model}">active${"\l"}/#if>"><a href="/${_model}"><i class="icon-home"></i> <span> ${_name?cap_first}</span></a></li>
