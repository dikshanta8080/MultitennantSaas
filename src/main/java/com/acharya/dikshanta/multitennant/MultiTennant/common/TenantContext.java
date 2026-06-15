package com.acharya.dikshanta.multitennant.MultiTennant.common;

public final class TenantContext {
    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();

    public static String getTenantId() {
        return TENANT_ID.get();
    }

    public static void setTenantId(String id) {
        TENANT_ID.set(id);
    }

    public static void clear() {
        TENANT_ID.remove();
    }


}
