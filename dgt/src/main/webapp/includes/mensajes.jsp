<c:if test="${mensaje != null || mensaje != borra}">
	<div class="alert alert-danger alert-dismissible fade show"
		role="alert"> ${mensaje}
	</div>
</c:if>