<#import "/app/views/_layouts/default.ftl" as layout>
<@layout.defaultLayout "Signup">
<h2>Signup</h2>
<form action="entry" method="POST">
<dl>
  <dt>E-mail</dt>
  <dd><input type="text" name="email" value="${(form.email)?html}"></dd>
</dl>
<div>
  <input type="submit" value="Submit">
</div>
</form>
</@layout.defaultLayout>
