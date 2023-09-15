package com.tsl3060.open.extend.core.secure;

import com.tsl3060.open.extend.core.Config;

import java.util.HashMap;

/**
 *
 */
public class SecureTool {

    private final HashMap<String, ISecure> secureHashMap = new HashMap<>();

    public SecureTool(Config config) {
        RSASecure rsaSecure = new RSASecure();
        rsaSecure.setConfig(config);
        secureHashMap.put(rsaSecure.name(), rsaSecure);
    }

    public ISecure getSecure(String name) {
        return secureHashMap.get(name);
    }

}
