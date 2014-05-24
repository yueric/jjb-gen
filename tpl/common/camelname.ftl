<#-- 驼峰命名法，将数据库中下划线改成首字母大写 -->
<#macro camelname nstr><#list nstr?split("_") as x>${x?cap_first}</#list></#macro>