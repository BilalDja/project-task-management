package me.djamelkorei.projecttaskmanagement.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Utility class for Servlet Request.
 *
 * @author Djamel Eddine Korei
 */
@Component
public class RequestUtils {

    public static HttpServletRequest httpServletRequest;

    public static Map<String, String> getPathVariables() {
        return (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    }

    public static Long getLongPathVariable(String key) {
        String value = getPathVariables().get(key);
        return value == null ? null : Long.parseLong(value);
    }

    @Autowired
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        RequestUtils.httpServletRequest = httpServletRequest;
    }

}
