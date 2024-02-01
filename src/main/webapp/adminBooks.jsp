<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
	<div class="container">
		<nav class="navbar navbar-expand-md navbar-light">
			<div>
				<a class="navbar-brand"> Library System </a>
			</div>
		</nav>
	
		<div class="row">
			<h1 class="text-center">List of Books</h1>
			<hr>
			
			<div class="text-end">
				<a href="new"
					class="btn btn-outline-primary">Add New Book</a>
			</div>
			<br>
			
			<!-- Create a table to list out all current users information -->
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Title</th>
						<th>ISBN</th>
						<th>Published</th>
						<th>Created</th>
						<th>Modified</th>
						<th>Actions</th>
					</tr>
				</thead>
				
				<!-- Pass in the list of users receive via the Servlet response to a loop -->
				<tbody>
<%-- 					<c:if test="${empty records}"> --%>
					<c:if test="${records.size() > 0}">
						<c:forEach var="record" items="${records}">
						<tr>
							<td><c:out value="${record.title}" /></td>
							<td><c:out value="${record.isbn}" /></td>
							<td>
								<c:if test="${record.published == 1}">Published</c:if><c:if test="${record.published == 0}">Unpublished</c:if>
							</td>
							<td><c:out value="${record.created}" /></td>
							<td><c:out value="${record.modified}" /></td>
							<td>
								<a class="btn btn-link act-edit" href="edit?id=<c:out value='${record.id}' />">Edit</a>
								<a class="btn btn-link act-delete" href="delete?id=<c:out value='${record.id}' />">Delete</a>
								<a class="btn btn-link act-publish" href="publish?id=<c:out value='${record.id}' />&published=<c:out value='${record.published}' />"
								><c:if test="${record.published == 1}">Unpublish</c:if><c:if test="${record.published == 0}">Publish</c:if></a>
							</td>
						</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			
			<c:if test="${records.size() == 0}">
				<p class="text-center">Book not found. Please create a new one.</p>
			</c:if>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function(){
			$('a.act-delete').on('click', function(e){
				if(!confirm("Delete this entry, proceed?")) {
					e.preventDefault();
				}
			});
		});
	</script>
</body>
