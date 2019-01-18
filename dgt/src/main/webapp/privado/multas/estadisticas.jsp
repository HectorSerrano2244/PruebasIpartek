<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<main role="main" class="container p-4">
<div class="container mb-3">
	<div class="row d-flex align-items-center mb-3">
		<div class="col-4">
			<a href="privado/principal.jsp"	class=" btn btn-outline-primary btn-block">Volver</a>
		</div>
		<div class="text-center align-middle col-8">
			<span style="font-size:25px">ESTADÍSTICAS</span>
		</div>
	</div>
	<!-- Actual -->
	<span>Objetivos actuales</span>
	<table id="actual" class="table">
		<tr>
			<td>Mes</td>
			<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${totalMesActual}" /> de <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${objetivoAnualActual / 12}" /></td>
		</tr>
		<tr>
			<td>Año</td>
			<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${totalAnualActual}" /> de <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${objetivoAnualActual}" /></td>
		</tr>
	</table>
	
	<hr>
	
	<!-- Histórico -->
	<p><span>Histórico</span>
		<span class="float-right">
			<select id="anyo" onchange="cambiarAnyo()">
				<c:forEach var = "i" begin = "2018" end ="${anyoActual}"  >
			  		<option value="${i}">${i}</option>
			    </c:forEach>
			</select>
		</span></p>
		<p>Objetivo : 98797987/12000</p>
	<table id="historico" class="table">
		<thead class="thead-light">
			<tr>
				<th>Mes</th>
				<th>Rec de Obj</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${objetivo}" var="o">
				<tr class="${(o.importe < (objetivoAnual / 12)) ? 'nocumple' : 'cumple'}">
					<td>${o.mes}</td>
					<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${o.importe}" /> de <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${objetivoAnual / 12}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table> 	
</div>

</main>
<%@include file="../../includes/footer.jsp"%>