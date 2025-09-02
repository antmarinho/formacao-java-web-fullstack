<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>
<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded"></div>
	<div class="pcoded-overlay-box"></div>
	<div class="pcoded-container navbar-wrapper">
		<jsp:include page="navbar.jsp"></jsp:include>
		<div class="pcoded-main-container">
			<div class="pcoded-wrapper">
				<jsp:include page="navbar-main-menu.jsp"></jsp:include>
				<div class="pcoded-content">
					<!-- Page-header start -->
					<jsp:include page="page-header.jsp"></jsp:include>
					<!-- Page-header end -->
					<div class="pcoded-inner-content">
						<!-- Main-body start -->
						<div class="main-body">
							<div class="page-wrapper">
								<!-- Page-body start -->
								<div class="page-body">
									<div class="row">
										<div class="col-sm-12">
											<!-- Basic Form Inputs card start -->
											<div class="card">
												<div class="card-block">
													<h4 class="sub-title">Relatorio Usuario</h4>
													<form class="form-material" action="<%= request.getContextPath()%>/ServletUserController" method="get" id="form-user">

														<input type="hidden" name="acao" value="imprimirRelatorio">

														<div class="form-row align-items-center">
															<div class="col-auto">
																<label class="sr-only" for="dataIni">Data Inicial</label>
																<input value="${dataIni}" type="text" class="form-control mb-2" id="dataIni" name="dataIni">
															</div>
															<div class="col-auto">
																<label class="sr-only" for="dataF">Data Final</label>
																<input value="${dataF}" type="text" class="form-control" id="dataF" name="dataF">
															</div>
															<div class="col-auto">
																<button type="submit" class="btn btn-primary mb-2">Imprimir</button>
															</div>
														</div>

													</form>
												</div>
											</div>
										</div>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="js-file.jsp"></jsp:include>
	<script type="text/javascript">
	
	$(function() {
		  
		  $("#dataIni").datepicker({
			    dateFormat: 'dd/mm/yy',
			    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
			    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			    nextText: 'Próximo',
			    prevText: 'Anterior'
			});
	});
	
	$(function() {
		  
		  $("#dataF").datepicker({
			    dateFormat: 'dd/mm/yy',
			    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
			    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			    nextText: 'Próximo',
			    prevText: 'Anterior'
			});
	});
	
	</script>
</body>
</html>