<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container text-center">
	<font color="red">${errorMessage}</font>
	<form method="post">
		User Id :<input name="name" type="text" /> Password :<input name="pwd"
			type="password" /> <input type="submit">
	</form>
</div>
<%@ include file="common/footer.jspf"%>