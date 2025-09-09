<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set scope="session" var="isAdmin"
	value='<%= request.getSession().getAttribute("isAdmin").toString() %>'></c:set>

<nav class="pcoded-navbar">
	<div class="sidebar_toggle">
		<a href="#"><i class="icon-close icons"></i></a>
	</div>
	<div class="pcoded-inner-navbar main-menu">
		<div class="">
			<div class="main-menu-header">
			<c:if test="${imagemUser != '' && imagemUser != null}">
				<img class="img-80 img-radius" src="${imagemUser}" alt="User-Profile-Image">
			</c:if>
			<c:if test="${imagemUser == '' || imagemUser == null}">
				<img class="img-80 img-radius" src="<%= request.getContextPath() %>/assets/images/avatar-blank.jpg" alt="User-Profile-Image">
			</c:if>
				<div class="user-details">
					<span id="more-details"><%= request.getSession().getAttribute("user") %><i
						class="fa fa-caret-down"></i></span>
				</div>
			</div>
			<div class="main-menu-content">
				<ul>
					<li class="more-details"><a
						href="<%= request.getContextPath() %>/ServletLogin?acao=logout"><i
							class="ti-layout-sidebar-left"></i>Logout</a></li>
				</ul>
			</div>
		</div>
		<ul class="pcoded-item pcoded-left-item">
			<li class="active"><a
				href="<%= request.getContextPath() %>/principal/principal.jsp"
				class="waves-effect waves-dark" style="margin-top: 10px;"> <span
					class="pcoded-micon"><i class="ti-home"></i><b>D</b></span> <span
					class="pcoded-mtext" data-i18n="nav.dash.main">Home</span> <span
					class="pcoded-mcaret"></span>
			</a></li>
			<li class="pcoded-hasmenu"><a href="javascript:void(0)"
				class="waves-effect waves-dark"> <span class="pcoded-micon"><i
						class="ti-layout-grid2-alt"></i></span> <span class="pcoded-mtext"
					data-i18n="nav.basic-components.main">Menu</span> <span
					class="pcoded-mcaret"></span>
			</a>
				<ul class="pcoded-submenu">
					<c:if test="${isAdmin}">
						<li class=" "><a
							href="<%= request.getContextPath() %>/ServletUserController?acao=listarUser"
							class="waves-effect waves-dark"> <span class="pcoded-micon"><i
									class="ti-angle-right"></i></span> <span class="pcoded-mtext"
								data-i18n="nav.basic-components.alert">Usuario</span> <span
								class="pcoded-mcaret"></span>
						</a></li>
					</c:if>
				</ul></li>
		</ul>
		<div class="pcoded-navigation-label" data-i18n="nav.category.forms">Relatorio</div>
			<ul class="pcoded-item pcoded-left-item">
				<li>
					<a href="<%= request.getContextPath()%>/principal/rel-user.jsp" class="waves-effect waves-dark"> 
						<span class="pcoded-micon">
							<i class="ti-layers"></i>
							<b>FC</b>
						</span> 
						<span class="pcoded-mtext" data-i18n="nav.form-components.main">Usuario</span> 
						<span class="pcoded-mcaret"></span>
					</a>
				</li>
				<li>
                	<a href="<%= request.getContextPath()%>/principal/rel-user-grafico.jsp" class="waves-effect waves-dark">
                        <span class="pcoded-micon">
                                <i class="ti-layers"></i>
                                <b>FC</b>
                        </span>
                        <span class="pcoded-mtext" data-i18n="nav.form-components.main">Grafico Salario</span>
                        <span class="pcoded-mcaret"></span>
                	</a>
                </li>
		</ul>
	</div>
</nav>