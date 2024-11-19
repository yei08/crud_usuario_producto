/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CRUDusuario;
import modelo.Usuario;

/**
 *
 * @author JEIFER ALCALA
 */
@WebServlet(name = "ServletUsuario", urlPatterns = {"/ServletUsuario"})
public class ServletUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String accion = request.getParameter("accion"); // Capturar la acción
    String redireccion = null;
        try {
   
   if (accion != null ){
    if (accion.equals("agregar")) {
        CRUDusuario crudAlguien = new CRUDusuario();
        crudAlguien.getAlguien().setPassword(request.getParameter("password"));
        crudAlguien.getAlguien().setNombre(request.getParameter("nombre"));
        crudAlguien.getAlguien().setApellidos(request.getParameter("apellidos"));
        crudAlguien.getAlguien().setRol(request.getParameter("rol"));
        crudAlguien.getAlguien().setEmail(request.getParameter("email"));
        crudAlguien.getAlguien().setTelefono(request.getParameter("telefono"));
        crudAlguien.getAlguien().setEstado(request.getParameter("estado"));
        crudAlguien.agregarUsuario();
       response.sendRedirect("web/usuario/agregar.jsp?mensaje=Usuario agregado al sistema");

    } else if (accion.equals("buscar")) {
        Usuario alguien = CRUDusuario.consultarUsuario(request.getParameter("id"));
        request.getSession().setAttribute("usuario.buscar", alguien);
        redireccion = request.getParameter("redir");

        if (redireccion != null) {
            if (redireccion.equals("borrar")) {
                response.sendRedirect("web/usuario/eliminar.jsp");
            } else if (redireccion.equals("modificar")) {
                response.sendRedirect("web/usuario/modificar.jsp");
            } else {
                response.sendRedirect("web/usuario/buscar.jsp");
            }
        }

    } else if (accion.equals("modificar")) {
        CRUDusuario crudAlguien = new CRUDusuario();
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                crudAlguien.getAlguien().setId(id);
            } catch (NumberFormatException e) {
                response.sendRedirect("web/error.jsp?mensaje=El ID proporcionado no es válido.");
                return;
            }
        } else {
            response.sendRedirect("web/error.jsp?mensaje=El ID es requerido.");
            return;
        }

        crudAlguien.getAlguien().setPassword(request.getParameter("password"));
        crudAlguien.getAlguien().setNombre(request.getParameter("nombre"));
        crudAlguien.getAlguien().setApellidos(request.getParameter("apellidos"));
        crudAlguien.getAlguien().setRol(request.getParameter("rol"));
        crudAlguien.getAlguien().setEmail(request.getParameter("email"));
        crudAlguien.getAlguien().setTelefono(request.getParameter("telefono"));
        crudAlguien.getAlguien().setEstado(request.getParameter("estado"));
        crudAlguien.modificarUsuario();
        response.sendRedirect("web/usuario/modificar.jsp?mensaje=Usuario modificado en el sistema");

    } else if (accion.equals("borrar")) {
        CRUDusuario crudAlguien = new CRUDusuario();
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                crudAlguien.getAlguien().setId(id);
            } catch (NumberFormatException e) {
                response.sendRedirect("web/error.jsp?mensaje=El ID proporcionado no es válido.");
                return;
            }
        } else {
            response.sendRedirect("web/error.jsp?mensaje=El ID es requerido.");
            return;
        }

        crudAlguien.eliminarUsuario();
        response.sendRedirect("web/usuario/eliminar.jsp?mensaje=Usuario eliminado del sistema");

    } else if (accion.equals("listartodo")) {
        Usuario[] listado = CRUDusuario.listarTodosLosUsuarios();
        request.getSession().setAttribute("usuario.listar", listado);
        response.sendRedirect("web/usuario/listar.jsp");

    } else if (accion.equals("login")) {
        Usuario alguien = CRUDusuario.iniciarSesion(request.getParameter("id"), request.getParameter("password"));
        if (alguien != null) {
            request.getSession().setAttribute("usuario.login", alguien);
            response.sendRedirect("index.jsp?mensaje=Bienvenido al Sistema");
        } else {
            response.sendRedirect("index.jsp?mensaje=Credenciales incorrectas");
        }

    } else if (accion.equals("salir")) {
        request.getSession().setAttribute("usuario.login", null);
        request.getSession().invalidate();
        response.sendRedirect("index.jsp?mensaje=Sesión cerrada");

    } else {
        response.sendRedirect("web/mensaje.jsp?mensaje=La acción solicitada no es válida");
    }
   } else {
    // Manejar el caso en que "accion" es null
    response.sendRedirect("web/mensaje.jsp?mensaje=No se especificó ninguna acción"); 
}
   
} catch (Exception error) {
    response.sendRedirect("web/mensaje.jsp?mensaje=" + error.getMessage());
} finally {
    out.close();
}

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
