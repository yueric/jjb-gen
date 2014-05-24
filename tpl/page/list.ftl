<#-- 列表页面 -->
${"\l"}#include "../common/_header.html"/>
${"\l"}#include "../common/_nav.html"/>
${"\l"}#include "../common/_mmenu.html"/>
${"\l"}#include "../common/_siderbar.html"/>
${"\l"}#include "../common/_premark.html"/>
<script type="text/javascript" src="lib/DataTables-1.9.4/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/DataTables-1.9.4/media/js/jquery.dataTables.bootstrap.js"></script>
<h2 style="margin-bottom: -1.75em;">${_model?cap_first}管理</h2><br/>
<div class="table_box">
	<table class="table table-first-column-number">
		 <thead>
		    <tr>
		      <#-- 列标题循环 -->
		      <#list _columns as x>
		      <th>${x.comment}</th>
		      </#list>
		      <th>操作&nbsp;&nbsp; <a href="/${_model}/add">创建${_model?cap_first}</a></th>
		    </tr>
		  </thead>
    	  <tbody>
			${"\l"}#list ${_model}Page.getList() as x>
			<tr>
				<#list _columns as x>
				<td style="text-align:left;">${r"$"}{x.${x.name}!}</td>
				</#list>
				<td style="text-align:left;">
					&nbsp;&nbsp;<a href="/${_model}/delete/${r"$"}{x.id}">删除</a>
					&nbsp;&nbsp;<a href="/${_model}/edit/${r"$"}{x.id}">修改</a>
				</td>
			</tr>
			${"\l"}/#list>
		</tbody>
	</table>
</div>
${"\l"}#include "../common/_page.html" />
${"\l"}@paginate currentPage=${_model}Page.pageNumber totalPage=${_model}Page.totalPage actionUrl="/${_model}/" />

${"\l"}#include "../common/_mark.html"/>
${"\l"}#include "../common/_foot.html"/>