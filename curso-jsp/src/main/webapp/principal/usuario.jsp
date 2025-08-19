<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

  <jsp:include page="head.jsp"></jsp:include>
  <body>
  <!-- Pre-loader start -->
  <jsp:include page="theme-loader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
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
                                                        <h4 class="sub-title">Cadastro de usuario</h4>
                                                        
                                                         <form class="form-material" action="<%= request.getContextPath()%>/ServletUserController" method="post" id="form-user">
                                                         
                                                         	<input type="hidden" name="acao" id="acao" value="">
                                                         	
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${mLogin.id}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID</label>
                                                            </div>
                                                            <div class="form-group form-default input-group mb-4">
                                                                <div class="input-group-prepend">
                                                                	<img alt="Imagem User" src="<%= request.getContextPath() %>/assets/images/avatar-blank.jpg" width="70px">
                                                                </div>
                                                                <input type="file" class="form-control-file" style="margin-top: 15px; margin-left: 5px;">
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${mLogin.nome}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="radio" name="sexo" checked="checked" value="MASCULINO" <%
                                                                
                                                                	ModelLogin mLogin = (ModelLogin) request.getAttribute("mLogin");
                                                                
                                                                	if(mLogin != null && mLogin.getSexo().equals("MASCULINO")) {
																	  out.print(" ");
																	  out.print("checked=\"checked\"");
																	  out.print(" ");
                                                                
                                                                %>>>Masculino</>
                                                                <input type="radio" name="sexo" checked="checked" value="FEMININO" <%
                                                                
                                                                	ModelLogin mLogin = (ModelLogin) request.getAttribute("mLogin");
                                                                
                                                                	if(mLogin != null && mLogin.getSexo().equals("FEMININO")) {
																	  out.print(" ");
																	  out.print("checked=\"checked\"");
																	  out.print(" ");
                                                                
                                                                %>>>FEMININO</>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="user" id="user" class="form-control" required="required" value="${mLogin.user}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Usuario</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${mLogin.email}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">E-mail (exa@gmail.com)</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
	                                                            <select class="form-control" aria-label="Default select example" name="perfil">
																  <option disabled="disabled">[Selecione o Perfil]</option>
																  <option value="ADMIN" <% 
																  	
																  	ModelLogin mLogin = (ModelLogin) request.getAttribute("mLogin");
																  
																  	if(mLogin != null && mLogin.getPerfil().equals("ADMIN")) {
																	  out.print(" ");
																	  out.print("selected=\"selected\"");
																	  out.print(" ");
																  } %>>Admin</option>
																  <option value="SECRETARIA <% 
																  
																	mLogin = (ModelLogin) request.getAttribute("mLogin");
																  
																  	if(mLogin != null && mLogin.getPerfil().equals("SECRETARIA")) {
																	  out.print(" ");
																	  out.print("selected=\"selected\"");
																	  out.print(" ");
																  } %>">Secretaria</option>
																  <option value="AUXILIAR" <% 
																  
																	mLogin = (ModelLogin) request.getAttribute("mLogin");
																  
																  	if(mLogin != null && mLogin.getPerfil().equals("AUXILIAR")) {
																	  out.print(" ");
																	  out.print("selected=\"selected\"");
																	  out.print(" ");
																  } %>>Auxiliar</option>
																</select>
																<span class="form-bar"></span>
                                                                <label class="float-label">Perfil</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="password" name="pass" id="pass" class="form-control" required="required" autocomplete="off" value="${mLogin.pass}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div>
                                                        
                                                          	<button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm()">Novo</button>
            												<button class="btn btn-success waves-effect waves-light">Salvar</button>
												            <!--  <button class="btn btn-info waves-effect waves-light">Editar</button>-->
												            <button type="button" class="btn btn-danger waves-effect waves-light" onclick="deleteAjax()">Excluir</button>
												            <!-- Button trigger modal -->
															<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModalUsuario">Pesquisar</button>
												           
												                                                          
                                                        </form>
             							</div>
             							</div>
             							</div>
             							</div>
             							<span id="msg" style="color: red">${msg}</span>
										<div style="height: 300px; overflow: scroll;">
											<table class="table" id="tabelaUsers">
											  <thead>
											    <tr>
											      <th scope="col">ID</th>
											      <th scope="col">Nome</th>
											      <th scope="col">Editar</th>
											    </tr>
											  </thead>
											  <tbody>
											    <c:forEach items="${mLogins}" var="ml">
											    	<tr>
											    		<td><c:out value="${ml.id}"></c:out></td>
											    		<td><c:out value="${ml.nome}"></c:out></td>
											    		<td><a class="btn btn-info" href="<%= request.getContextPath() %>/ServletUserController?acao=editar&id=${ml.id}">Ver</a></td>
											    	</tr>
											    </c:forEach>
											  </tbody>
											</table>
										</div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="js-file.jsp"></jsp:include>
    <!-- Modal -->
	<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de usuario</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <div class="input-group mb-3">
  				<input id="nomeBusca" type="text" class="form-control" placeholder="Nome" aria-label="nome" aria-describedby="basic-addon2">
  				<div class="input-group-append">
    				<button class="btn btn-success" type="button" onclick="buscarUsuario()">Buscar</button>
  				</div>
			</div>
			<div style="height: 300px; overflow: scroll;">
				<table class="table" id="tabela">
				  <thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">Nome</th>
				      <th scope="col">Editar</th>
				    </tr>
				  </thead>
				  <tbody>
				    
				  </tbody>
				</table>
			</div>
			<span id="total"></span>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>
    <script type="text/javascript">
    
    	function editar(id) {
    		
    		var urlAction = document.getElementById("form-user").action;
    		
    		window.location.href = urlAction + "?acao=editar&id=" + id;
			
		}
    
    	function buscarUsuario() {
    		
    		var nome = document.getElementById("nomeBusca").value;
    		
    		if(nome != null && nome != "" && nome.trim() != "") {
    			
    			var urlAction = document.getElementById("form-user").action;
    			
    			$.ajax({
    				
    				method: "get",
    				url: urlAction,
    				data: "nome=" + nome + "&acao=buscarAjax",
    				success: function(response) {

    					var json = JSON.parse(response);
    					
    					$('#tabela > tbody > tr').remove();
    					
    					for(let i = 0; i < json.length; i++) 
    						$('#tabela > tbody').append('<tr> <td>' + json[i].id + '</td> <td>' + json[i].nome + '</td> <td> <button onclick="editar(' + json[i].id + ')" class="btn btn-info">Ver</button> </td> </tr>');
						
    					document.getElementById("total").textContent = "Resultados: " + json.length;
					}
    				
    				
    			}).fail(function(xhr,status,errorThrown){
    				
    				alert("Erro ao buscar usuario por nome: " + xhr.responseText);
    			});
    		}
			
		}
    
    	function limparForm() {
    		
    		var elementos = document.getElementById("form-user").elements;
    		
    		for(i = 0; i < elementos.length; i++) {
    			
    			elementos[i].value = "";
    			
    		}
			
		}
    	
    	function deleteAjax() {
			
    		if(confirm("Deseja realmente excluir os dados")) {
    			
    			var urlAction = document.getElementById("form-user").action;
    			var id = document.getElementById("id").value;
    			
    			$.ajax({
    				
    				method: "get",
    				url: urlAction,
    				data: "id=" + id + "&acao=deletarAjax",
    				success: function(response) {
    					
    					limparForm();
    					
    					document.getElementById("msg").textContent = response;
    					
    					alert(response);
					}
    				
    				
    			}).fail(function(xhr,status,errorThrown){
    				
    				alert("Erro ao deletar usuario por id: " + xhr.responseText);
    			});
    		}
		}
    	
    	function criarDelete() {
    		
    		if(confirm("Deseja realmente excluir os dados?")) {
    			
    			document.getElementById("form-user").method = "get";
        		document.getElementById("acao").value = "deletar";
        		document.getElementById("form-user").submit();
    			
    		}
			
		}
    </script>
</body>
</html>