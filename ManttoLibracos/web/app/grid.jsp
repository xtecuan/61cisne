<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1" cellspacing="1" cellpadding="1">
    <thead>
        <tr>
            <th>Id</th>
            <th>Carnet</th>
            <th>Nombres</th>
            <th>Apellidos</th>
            <th>Correo</th>
            <th>Fecha Nac</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>



        <c:if test="${not empty respuestaGrid}">

            <c:forEach items="${respuestaGrid}" var="alumno">
                <tr>
                    <td>${alumno.id}</td>
                    <td>${alumno.carnet}</td>
                    <td>${alumno.nombres}</td>
                    <td>${alumno.apellidos}</td>
                    <td>${alumno.correo}</td>
                    <td>
                        <fmt:formatDate  value="${alumno.fechanac}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>
                        <c:url value="/dml.ufg" var="deleteAl">
                            <c:param name="action" value="remove"/>
                            <c:param name="id" value="${alumno.id}"/>
                            <c:param name="errorPage" value="/app/alumnosGrid.jsp"/>
                            <c:param name="viewPage" value="/app/alumnosGrid.jsp"/>
                        </c:url>

                        <a href="${deleteAl}">Borrar</a>


                        <c:url value="/app/alumnosEditar.jsp" var="editAl">
                            <c:param name="action" value="edit"/>
                            <c:param name="id" value="${alumno.id}"/>
                            <c:param name="carnet" value="${alumno.carnet}"/>
                            <c:param name="nombres" value="${alumno.nombres}"/>
                            <c:param name="apellidos" value="${alumno.apellidos}"/>
                            <c:param name="correo" value="${alumno.correo}"/>
                            <c:param name="fechanac" value="${alumno.fechanac}"/>
                          
                        </c:url>

                        <a href="${editAl}">Editar</a>

                    </td>
                </tr>
            </c:forEach>

            <c:remove scope="session" var="respuestaGrid"/>

        </c:if>

        <c:if test="${not empty errores}">

            <c:forEach items="${errores}" var="error">

                <tr>
                    <td colspan="6">${error}</td>
                </tr>

            </c:forEach>
            <c:remove scope="session" var="errores"/>
        </c:if>
    </tbody>
</table>