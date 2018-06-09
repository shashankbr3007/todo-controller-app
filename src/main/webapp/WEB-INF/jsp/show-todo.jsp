<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

				<div class="container">
					<table class="table table-striped">
						<caption>Your todos are</caption>
						<thead>
							<tr>
								<th>Description</th>
								<th>Target Date</th>
								<th>Is it Done?</th>
								<th/>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${todos}" var="todo">
								<tr>
									<td name="desc">${todo.desc}</td>
									<td><fmt:formatDate value="${todo.targetDate}" pattern="MM/dd/yyyy"/></td>
									<td name="done">${todo.done}</td>
									<td name="update">
										<a type="button" class="btn btn-success"
                        							href="/update-todo?id=${todo.id}">Update</a>
									</td>
									<td name="delete">
										<a type="button" class="btn btn-warning"
							href="/delete-todo?id=${todo.id}">Delete</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div>
						<a class="button" href="/add-todo">Add a Todo</a>
					</div>
				</div>

<%@ include file="common/footer.jspf" %>