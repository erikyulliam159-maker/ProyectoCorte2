/**
 * Clase SecurityFilter
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.util
 *
 * Descripción: Documentación pendiente.
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityFilter.
 */
@WebFilter("/Admin.xhtml")
public class SecurityFilter implements Filter {

    /**
     * Inicializa el.
     *
     * @param filterConfig the filter config
     * @throws ServletException the servlet exception
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      
    }

    /**
     * Do filter.
     *
     * @param request the request
     * @param response the response
     * @param chain the chain
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
      
        boolean loggedIn = (session != null && session.getAttribute("usuarioLogueado") != null);
        boolean isAdmin = (session != null && "administrador".equals(session.getAttribute("tipoUsuario")));
        
        if (loggedIn && isAdmin) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
        }
    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {
   
    }
}