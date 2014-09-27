<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="paginator" uri="/WEB-INF/tlds/paginator"%>
<c:if test="${listSize ne 0}">
	<c:url var="searchUri" value="/secure/funcionario?pagina=##"/>
	<paginator:display maxLinks="10" currPage="${pagina}" totalPages="${numeroDePaginas}" uri="${searchUri}" />
</c:if>