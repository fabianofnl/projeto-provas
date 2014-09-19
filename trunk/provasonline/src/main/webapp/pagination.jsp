<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="paginator" uri="/WEB-INF/tlds/paginator"%>

<c:url var="searchUri" value="/secure/funcionario?page=##"/>
<paginator:display maxLinks="10" currPage="${page}" totalPages="${totalPages}" uri="${searchUri}" />