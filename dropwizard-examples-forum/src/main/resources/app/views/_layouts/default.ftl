<#macro defaultLayout title="">
<html>
<head>
<#if title?has_content>
  <title>${title?html} | dropwizard-forum</title>
<#else>
  <title>dropwizard-forum</title>
</#if>
</head>
<body>
<div id="wrapper">
  <div id="header">
    <h1>Dropwizard Examples | Forum</h1>
  </div>
  <div id="content">
    <#nested/>
  </div>
  <div id="footer">
    <hr>
    <p>&copy; Copyright 2014 <a href="http://github.com/tachesimazzoca">tachesimazzoca</a>.</p>
  </div>
</div>
</body>
</html></#macro>
