package com.ajpb.eval1slfbp2.application.registry;

public interface ServiceRegistry {
    public <T> AdapterService<T> getService(String serviceName);
}
