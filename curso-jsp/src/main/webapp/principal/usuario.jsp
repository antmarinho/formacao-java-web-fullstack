<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                                                        
                                                         <form class="form-material" action="<%= request.getContextPath()%>/ServletUserController" method="post">
                                                            <div class="form-group form-default">
                                                                <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${mLogin.id}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID</label>
                                                            </div>
                                                            <div class="form-group form-default">
                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${mLogin.nome}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome</label>
                                                            </div>
                                                            <div class="form-group form-default">
                                                                <input type="text" name="user" id="user" class="form-control" required="required" value="${mLogin.user}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Usuario</label>
                                                            </div>
                                                            <div class="form-group form-default">
                                                                <input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${mLogin.email}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">E-mail (exa@gmail.com)</label>
                                                            </div>
                                                            <div class="form-group form-default">
                                                                <input type="password" name="pass" id="pass" class="form-control" required="required" autocomplete="off" value="${mLogin.pass}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div>
                                                        
                                                          <button class="btn btn-primary waves-effect waves-light">Novo</button>
            											<button class="btn btn-success waves-effect waves-light">Salvar</button>
												           <button class="btn btn-info waves-effect waves-light">Editar</button>
												           <button class="btn btn-danger waves-effect waves-light">Excluir</button>
												           
												                                                          
                                                        </form>
             							</div>
             							</div>
             							</div>
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
</body>
</html>