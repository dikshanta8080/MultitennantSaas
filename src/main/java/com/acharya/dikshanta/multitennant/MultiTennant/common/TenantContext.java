package com.acharya.dikshanta.multitennant.MultiTennant.common;

import java.util.UUID;

public final class TenantContext {
    private static final ThreadLocal<UUID> TENANT_ID = new ThreadLocal<>();

    public static UUID getTenantId() {
        return TENANT_ID.get();
    }

    public static void setTenantId(UUID id) {
        TENANT_ID.set(id);
    }

    public static void clear() {
        TENANT_ID.remove();
    }


}
