<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav>
	<div class="navigation-bar dark">
		<div class="navigation-bar-content container">
			<a href="${pageContext.request.contextPath}/secure/home.jsp" class="element">
				<span class="icon-grid-view"></span> SGPO
			</a>
			<span class="element-divider"></span>
			<ul class="element-menu">
				<c:if test="${usuario.perfil.role eq 'ROLE_ADMIN'}">
					<li>
						<a class="dropdown-toggle" href="#">Funcionario</a>
						<ul class="dropdown-menu dark" data-role="dropdown">
							<li><a href="${pageContext.request.contextPath}/secure/funcionario.jsp">Gerenciar</a></li>
						</ul>
					</li>
				</c:if>
				<c:if test="${usuario.perfil.role eq 'ROLE_ADMIN'}">
					<li>
						<a href="#" class="dropdown-toggle fg-yellow">Provas</a>
						<ul class="dropdown-menu dark" data-role="dropdown">
							<li><a href="#">Resultados</a></li>
							<li class="divider"></li>
							<li>
								<a href="#" class="dropdown-toggle">Gerenciar</a>
								<ul class="dropdown-menu dark" data-role="dropdown">
									<li><a href="#">Cadastrar Temas</a></li>
									<li><a href="#">Cadastrar Questões</a></li>
									<li><a href="#">Agendar Provas</a></li>
								</ul>
							</li>
						</ul>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>
