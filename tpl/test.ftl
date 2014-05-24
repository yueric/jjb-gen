<#include "./common/camelname.ftl">
<html>
<head>
<title>Welcome!</title>
</head>
<body>
<h1>Welcome ${user}!</h1>
<p>Our latest product:
<a href="${latestProduct.url}"> ${latestProduct.name?cap_first}</a>!
<@camelname latestProduct.table />
${latestProduct.name?cap_first}
</body>
</html>
${'\l'}#include "_form.html" />

