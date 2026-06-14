package com.acharya.dikshanta.multitennant.MultiTennant.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Component
public class TenantFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String tenantId = req.getHeader("X-Tenant-Id");
            if (tenantId != null) {
                TenantContext.setTenantId(UUID.fromString(tenantId));
            }
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }

    }
}
