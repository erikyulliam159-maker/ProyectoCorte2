package co.edu.unbosque.util;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/Admin.xhtml")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización del filtro
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        // Verificar si el usuario está logueado y es administrador
        boolean loggedIn = (session != null && session.getAttribute("usuarioLogueado") != null);
        boolean isAdmin = (session != null && "administrador".equals(session.getAttribute("tipoUsuario")));
        
        if (loggedIn && isAdmin) {
            // Usuario autenticado y es administrador, permitir acceso
            chain.doFilter(request, response);
        } else {
            // Redirigir al login si no está autenticado o no es administrador
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
        }
    }

    @Override
    public void destroy() {
        // Cleanup code
    }
}