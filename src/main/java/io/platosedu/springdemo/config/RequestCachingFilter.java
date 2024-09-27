package io.platosedu.springdemo.config;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class RequestCachingFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestCachingFilter.class);
    private final static String USER_AGENT_HEADER = "user-agent";
    private final static String KUBE_PROBE_USER_AGENT = "kube-probe";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var userAgent = request.getHeader(USER_AGENT_HEADER);
        if (userAgent != null && userAgent.contains(KUBE_PROBE_USER_AGENT)) {
            filterChain.doFilter(request, response);
            return;
        }
        CachedHttpServletRequest cachedHttpServletRequest = new CachedHttpServletRequest(request);
        LOGGER.info("REQUEST DATA: " + IOUtils.toString(cachedHttpServletRequest.getInputStream(), StandardCharsets.UTF_8));
        Collections.list(request.getHeaderNames()).forEach(headerName -> {
            Collections.list(request.getHeaders(headerName)).forEach(headerValue -> LOGGER.info("REQUEST HEADER -> {}:{}", headerName, headerValue));
        });
        filterChain.doFilter(cachedHttpServletRequest, response);
    }
}