<%-- 
    Document   : login
    Created on : 17 nov 2024, 2:47:14 a.m.
    Author     : JEIFER ALCALA
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="tuPaquete.CRUDusuario" %> <%-- Importa la clase CRUDusuario --%>
<%@ page import="tuPaquete.Usuario" %> <%-- Importa la clase Usuario --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Iniciar Sesión</h1>

        <%-- Mensaje de error en caso de que se haya producido un error --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <p style="color:red"><%= request.getAttribute("errorMessage") %></p>
        <% } %>

        <%-- Formulario de inicio de sesión --%>
        <form action="<%= request.getContextPath() %>/Controllers/ServletUsuario.jsp?action=authenticate" method="post">
            <label for="id">ID de Usuario:</label> 
            <input type="text" name="id" id="id" required> 
            <br>
            <label for="password">Contraseña:</label>
            <input type="password" name="password" id="password" required>
            <br>
            <input type="submit" value="Iniciar Sesión">
        </form>

        <br>
        <a href="<%= request.getContextPath() %>/index.jsp">Volver a Inicio</a>
    </body>
</html>