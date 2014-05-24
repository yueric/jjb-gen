<#--  按db生成jfinal其他相关需要的相关配置 -->
1.Route配置，添加到configRoute方法中
<#list tables as x>
me.add("/${x._model}", ${x._model?cap_first}Controller.class);
</#list>

2.ormapping配置，添加到configPlugin方法中
<#list tables as x>
arp.addMapping("${x._name}", ${x._model?cap_first}.class);
</#list>

2.菜单配置，添加于common/_sidebar.html中，请修改对应的中文注解
<#list tables as x>
<li class="${"\l"}#if _MODULE?exists && _MODULE=="${x._model}">active${"\l"}/#if>"><a href="/${x._model}"><i class="icon-home"></i><span>${x._model?cap_first}</span></a></li>
</#list>
