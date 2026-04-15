package com.dummyjson.core.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.aeonbits.owner.ConfigCache;

import com.dummyjson.core.exeption.ConfigurationException;
import com.dummyjson.core.interfaces.Configuration;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigurationManager {

    private static final ThreadLocal<Configuration> instance = new ThreadLocal<>();

    public static Configuration getConfiguration() throws ConfigurationException {
        if (instance.get() == null) {
            try {
                instance.set(ConfigCache.getOrCreate(Configuration.class));
            } catch (Exception ex) {
                throw new ConfigurationException("Failed to get configuration properties", ex);
            }
        }
        return instance.get();
    }
}
