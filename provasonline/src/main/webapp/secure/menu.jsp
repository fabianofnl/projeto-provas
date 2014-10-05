<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav>
	<div class="navigation-bar dark">
		<div class="navigation-bar-content container">
			<a href="${pageContext.request.contextPath}/secure/home.jsp" class="element">
				<span class="icon-home"></span> Provas Online
			</a>
			<span class="element-divider"></span>
			<ul class="element-menu">
				<c:if test="${funcionario.role eq 'ROLE_ADMIN' or funcionario.role eq 'ROLE_COLABORADOR'}">
					<li>
						<a class="dropdown-toggle" href="#">Exames</a>
						<ul class="dropdown-menu dark" data-role="dropdown">
							<li><a href="${pageContext.request.contextPath}/secure/apostilas">Apostilas</a></li>
							<li><a href="${pageContext.request.contextPath}/secure/realizarExame">Realizar</a></li>
						</ul>
					</li>
				</c:if>
			
				<c:if test="${funcionario.role eq 'ROLE_ADMIN' or funcionario.role eq 'ROLE_INSTRUTOR'}">
					<li>
						<a class="dropdown-toggle" href="#">Funcionario</a>
						<ul class="dropdown-menu dark" data-role="dropdown">
							<li><a href="${pageContext.request.contextPath}/secure/cadastrarFuncionario">Cadastrar</a></li>
							<li><a href="${pageContext.request.contextPath}/secure/funcionario">Consultar</a></li>
							<li class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/secure/associarFuncionario">Associar Equipes</a></li>
							<li><a href="${pageContext.request.contextPath}/secure/listarEquipes">Consultar Equipes</a></li>
							<li class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/secure/relatorioFuncionario">Consultar Relatório</a></li>
						</ul>
					</li>
				</c:if>
				<c:if test="${funcionario.role eq 'ROLE_ADMIN' or funcionario.role eq 'ROLE_INSTRUTOR'}">
					<li>
						<a href="#" class="dropdown-toggle fg-yellow">Provas</a>
						<ul class="dropdown-menu dark" data-role="dropdown">
							<li><a href="${pageContext.request.contextPath}/secure/temas">Cadastrar Temas</a></li>
							<li><a href="${pageContext.request.contextPath}/secure/cadastrarQuestoes">Cadastrar Questões</a></li>
							<li class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/secure/montarProvas">Montar Provas</a></li>
							<li><a href="${pageContext.request.contextPath}/secure/vincularApostilas">Vincular Apostilas</a></li>
							<li><a href="${pageContext.request.contextPath}/secure/agendarProvas">Agendar Provas</a></li>	
						</ul>
					</li>
				</c:if>
			</ul>

			<div class="element place-right">
				<a class="dropdown-toggle" href="#">
					<span class="icon-cog"></span>
				</a>
				<ul class="dropdown-menu dark place-right" data-role="dropdown">
					<li><a href="${pageContext.request.contextPath}/logout">Sair</a></li>
				</ul>
			</div>
			<span class="element-divider place-right"></span>
			<button class="element place-right">${funcionario.nome}</button>

<%--
			<ul class="element-menu drop-left place-right">
				<li>
					<a class="dropdown-toggle place-right" href="#">${usuario.nome}</a>
					<ul class="dropdown-menu dark" data-role="dropdown">
						<li><a href="#">Logout</a></li>
					</ul>
				</li>
			</ul>
 --%>
		</div>
	</div>
</nav>
