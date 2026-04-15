package com.dummyjson.core.interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "classpath:config/api.properties",
        "classpath:config/general.properties"
})
public interface Configuration extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("client.id")
    String clientId();

    @Key("client.secret")
    String clientSecret();

    @Key("http.socket.timeout")
    Integer httpSocketTimeout();

    @Key("http.connection.timeout")
    Integer httpConnectionTimeout();

    @Key("http.connection-manager.timeout")
    Integer httpConnectionManagerTimeout();

}
