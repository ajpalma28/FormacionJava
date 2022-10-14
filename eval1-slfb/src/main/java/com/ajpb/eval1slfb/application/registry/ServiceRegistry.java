package com.ajpb.eval1slfb.application.registry;

public interface ServiceRegistry {
    public <T> AdapterService<T> getService(String serviceName);
}
