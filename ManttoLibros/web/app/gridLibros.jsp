<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1" cellspacing="1" cellpadding="1">
    <thead>
        <tr>
            <th>Id</th>
            <th>Codigo</th>
            <th>Nombre</th>
            <th>Autor</th>
            <th>ISBN</th>
            <th>Fecha Impresion</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>



        <c:if test="${not empty respuestaGrid}">

            <c:forEach items="${respuestaGrid}" var="libro">
                <tr>
                    <td>${libro.id_libro}</td>
                    <td>${libro.codigo}</td>
                    <td>${libro.nombre}</td>
                    <td>${libro.autor}</td>
                    <td>${alumno.isbn}</td>
                    <td>
                        <fmt:formatDate  value="${libro.fechaimpresion}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>
                        <c:url value="/DMLLibros" var="deleteAl">
                            <c:param name="action" value="remove"/>
                            <c:param name="id" value="${libro.id_libro}"/>
                            <c:param name="errorPage" value="/app/librosGrid.jsp"/>
                            <c:param name="viewPage" value="/app/librosGrid.jsp"/>
                        </c:url>

                        <a href="${deleteAl}">Borrar</a>


                        <c:url value="/app/librosEditar.jsp" var="editAl">
                            <c:param name="action" value="edit"/>
                            <c:param name="id" value="${libro.id_libro}"/>
                            <c:param name="carnet" value="${libro.codigo}"/>
                            <c:param name="nombres" value="${libro.nombre}"/>
                            <c:param name="apellidos" value="${libro.autor}"/>
                            <c:param name="correo" value="${libro.isbn}"/>
                            <c:param name="fechanac" value="${libro.fechaimpresion}"/>
                          
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