
package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // usuario que ingresa por primera vez al sitio
        boolean nuevoUsuario = true;
        // Obtener arreglo de Cookies existentes en el servidor
        Cookie[] cookies = request.getCookies();
        // Verificar si ya existe la cookie
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("visitanteRecurente") && cookie.getValue().equals("si")){
                    
                    // Si existe la cookie y el visitante es recurente
                    nuevoUsuario = false;
                    break;
                }
            }
        }
        
        String mensaje = null;
        
        if (nuevoUsuario) {
            //Creamos la cookie
            Cookie visitanteN = new Cookie("visitanteRecurente","si");
            response.addCookie(visitanteN);
            mensaje = "Bienvenido a Nuestro Sitio Web";
        }else{
            mensaje = "Gracias por visitarnos Nuevamente";
        }
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.println(mensaje);
        out.close();
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

}
