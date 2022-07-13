<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<table class="table table-striped">
		<caption>Your todos are:</caption>
		<thead>
			<tr>
				<th>DESCRIPTION</th>
				<th>TARGET DATE</th>
				<th>IS IT DONE?</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.desc}</td>
					<td><fmt:formatDate value="${todo.targetDate}" /></td>
					<td>${todo.isDone}</td>
					<td><a type="button" class="btn btn-warning"
						href="/update-todos?id=${todo.id}">Update</a></td>
					<td><a type="button" class="btn btn-danger"
						href="/delete-todos?id=${todo.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a class="button" href=/add-todos>Add a todo</a>
	</div>
</div>
<%@ include file="common/footer.jspf"%>