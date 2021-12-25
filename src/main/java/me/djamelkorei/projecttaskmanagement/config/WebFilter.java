package me.djamelkorei.projecttaskmanagement.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * Filter to permit SPA Assets. I only use this for test experience ( not required for production )
 *
 * @author Djamel Eddine Korei
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getRequestURI();
        if (!path.startsWith("/api")
                && !path.equals("/h2-console")
                && !path.equals("/")
                && !path.endsWith(".js") && !path.endsWith(".css") && !path.endsWith(".png") && !path.endsWith(".jpg")) {
            req = new HttpServletRequestWrapper(request) {
                @Override
                public String getRequestURI() {
                    return "/";
                }
            };
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) {
    }

}
