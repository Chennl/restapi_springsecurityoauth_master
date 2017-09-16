package com.oak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Environment environment;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(jwtTokenEnhancer())
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        String pwd = environment.getProperty("keystore.password");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource("jwt.jks"),
                pwd.toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        return converter;
    }

    @Autowired
    DataSource dataSource;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      // clients.inMemory()
        clients.jdbc(dataSource);
//                .withClient("service-account-1")
//                .secret("service-account-1-secret")
//                .authorizedGrantTypes("client_credentials")
//                .scopes("resource-server-read", "resource-server-write");
        //        .authorities("ROLE_RS_READ");

        /*
            第一种: client_credentials
                1.http://localhost:9080/oauth/token?grant_type=authorization_code
                2.浏览器会让你输入用户名密码: client_id,client_secret
                3.来换取access_token
            第二种: Authorization_code方式:
               1. http://localhost:9080/oauth/authorize?client_id=service-account-2&response_type=code&redirect_uri=http://localhost/apiwebclient/auth_code.html
               2.浏览器会让你输入用户名密码，这是因为 Spring Security 在默认情况下会对所有URL添加Basic Auth认证.
                    默认: username: user, Using default security password: e0cfe6ad-95d0-4e4e-8962-a5aac675717b
               3.Authorize页面，然后，点击Authorize后，浏览器就会重定向到auth_code.html，并带上code参数
               4.拿到code以后，就可以调用POST  http://localhost:9080/oauth/token?code=xTCSFg; 来换取access_token
         */

    }
}