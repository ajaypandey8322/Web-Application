	<%@ include file="common/header.jsp"%>
	<%@ include file="common/navigation.jsp"%>
		
		<div class="container">
		
			<h1>Below is your To do List - </h1>
		
			<table class ="table">
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is Completed</th>
					<th>Action</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var ="todo">
					<tr>
						<td>${todo.description}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
						<td><a href="delete-todo?id=${todo.id}" class="btn btn-warning" >Delete</td>
						<td><a href="update-todo?id=${todo.id}" class="btn btn-success" >Update</td>
	
					</tr>
				</c:forEach>
			</tbody>
			</table>
			
			<a href="add-todo" class="btn btn-success">Add Todo</a>
			
		</div>	
		<%@ include file="common/footer.jsp"%>	