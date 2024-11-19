<%-- 
    Document   : index
    Created on : 17 nov 2024, 2:47:58 a.m.
    Author     : JEIFER ALCALA
--%>
<%@ page import="modelo.CRUDusuario" %>
<%@ page import="modelo.Usuario" %>
<%
    if (request.getSession().getAttribute("usuario.login") == null){
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>menu de la aplicacion</title>
    </head>
    <body>
<center>
    <table border="0">
        <tbody>
            <tr>
                <th><h1><a href="web/usuario/agregar.jsp">Agregar</a></h1></th>
            </tr>
            <tr>
                <td><h1><a href="web/usuario/buscar.jsp">Buscar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="web/usuario/modificar.jsp">Modificar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="web/usuario/eliminar.jsp">Eliminar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="usuario?accion=listartodo">Listar</a></h1></td>
            </tr>
            <tr>
                <td><h1><a href="/ejemplosesion/usuario?accion=logout">Salir</a></h1></td>
            </tr>
        </tbody>
    </table>
</center>
</body>
</html>
