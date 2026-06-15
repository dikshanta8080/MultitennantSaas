package com.acharya.dikshanta.multitennant.MultiTennant.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class TenantFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String tenantId = req.getHeader("X-Tenant-Id");

        if (tenantId == null || tenantId.isBlank()) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.setContentType("application/json");

            Map<String, Object> errors = new HashMap<>();
            errors.put("status", false);
            errors.put("message", "Provide valid tenant id");

            res.getWriter().write(
                    new ObjectMapper().writeValueAsString(errors)
            );
            return;
        }

        try {
            TenantContext.setTenantId(tenantId);
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}