package com.dummyjson.core.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.dummyjson.core.config.ApiConfig;
import com.dummyjson.core.interfaces.HttpMethods;
import com.dummyjson.core.utils.LogUtils;
import com.dummyjson.core.utils.StringUtils;
import com.dummyjson.data.models.Authentication;

import static com.dummyjson.core.config.ConfigurationManager.getConfiguration;
import static io.restassured.RestAssured.given;

public abstract class BaseApi extends ApiConfig implements HttpMethods {

    protected Authentication authentication;
    protected RequestSpecification reqSpec;
    protected ResponseSpecification resSpec;

    protected BaseApi() {
        this(
                new RequestSpecBuilder()
//                        .log(LogDetail.METHOD).log(LogDetail.URI).log(LogDetail.HEADERS).log(LogDetail.PARAMS).log(LogDetail.BODY)
                        .build(),
                new ResponseSpecBuilder()
//                        .log(LogDetail.ALL)
                        .build()
        );
    }

    protected BaseApi(RequestSpecification reqSpec, ResponseSpecification resSpec) {
        this.reqSpec = reqSpec;
        this.resSpec = resSpec;
    }

    private String formatRoles(final String roles) {
        return roles.replaceAll(" ", "")
                .replaceAll("\\[", "")
                .replaceAll("]", "");
    }

    protected BaseApi setAuthentication(final Authentication authentication) {
        this.authentication = authentication;
        return this;
    }

    protected Map<String, String> tokenHeader() {
        Map<String, String> header = new HashMap<>();
        if (Objects.isNull(this.authentication) || StringUtils.isNullOrEmpty(this.authentication.getToken())) {
            return header;
        }
        header.put("Authorization", "Bearer " + this.authentication.getToken());
        return header;
    }

    protected Map<String, String> setApiClientCredentialsHeader() {
        if (StringUtils.isNullOrEmpty(getConfiguration().clientId()) ||
                StringUtils.isNullOrEmpty(getConfiguration().clientSecret())) {
            LogUtils.logError("Api Connect credentials are null or empty");
            return new HashMap<>();
        }
        Map<String, String> header = new HashMap<>();
        header.put("x-ibm-client-id", getConfiguration().clientId());
        header.put("x-ibm-client-secret", getConfiguration().clientSecret());
        return header;
    }



    public Response GET(boolean isLog) {
        response = given()
                .relaxedHTTPSValidation()
                .cookies(cookies)
                .cookie(cookie)
                .spec(this.reqSpec)
                .pathParams(pathParams)
                .queryParams(queryParams)
                .formParams(formParams)
                .headers(headers)
                .noContentType()
                .when()
                .get(url)
                .then()
                .spec(this.resSpec)
                .extract()
                .response();

        if (isLog) super.log("GET");
        resetRequest();
        return response;
    }

    @Override
    public Response GET() {
        return this.GET(true);
    }

    public Response POST(boolean isLog) {
        response = given()
                .relaxedHTTPSValidation()
                .spec(this.reqSpec)
                .cookies(cookies)
                .cookie(cookie)
                .pathParams(pathParams)
                .queryParams(queryParams)
                .formParams(formParams)
                .headers(headers)
                .contentType(contentType)
                .body(body)
                .when()
                .post(url)
                .then()
                .spec(this.resSpec)
                .extract()
                .response();

        if (isLog) super.log("POST");
        resetRequest();
        return response;
    }

    public Response POSTWithoutBody() {
        response = given()
                .relaxedHTTPSValidation()
                .spec(this.reqSpec)
                .cookies(cookies)
                .cookie(cookie)
                .pathParams(pathParams)
                .queryParams(queryParams)
                .formParams(formParams)
                .headers(headers)
                .contentType(contentType)
                .when()
                .post(url)
                .then()
                .spec(this.resSpec)
                .extract()
                .response();
        super.log("POST");
        resetRequest();
        return response;
    }

    @Override
    public Response POST() {
        return this.POST(true);
    }

    @Override
    public Response PUT() {
        response = given()
                .relaxedHTTPSValidation()
                .spec(this.reqSpec)
                .cookies(cookies)
                .cookie(cookie)
                .pathParams(pathParams)
                .queryParams(queryParams)
                .formParams(formParams)
                .headers(headers)
                .contentType(contentType)
                .body(body)
                .when()
                .put(url)
                .then()
                .spec(this.resSpec)
                .extract()
                .response();

        super.log("PUT");
        resetRequest();
        return response;
    }

    @Override
    public Response PATCH() {
        response = given()
                .relaxedHTTPSValidation()
                .spec(this.reqSpec)
                .cookies(cookies)
                .cookie(cookie)
                .pathParams(pathParams)
                .queryParams(queryParams)
                .formParams(formParams)
                .headers(headers)
                .contentType(contentType)
                .body(body.toString())
                .when()
                .patch(url)
                .then()
                .spec(this.resSpec)
                .extract()
                .response();

        super.log("PATCH");
        resetRequest();
        return response;
    }

    @Override
    public Response DELETE() {
        response = given()
                .relaxedHTTPSValidation()
                .spec(this.reqSpec)
                .cookies(cookies)
                .cookie(cookie)
                .pathParams(pathParams)
                .headers(headers)
                .noContentType()
                .when()
                .delete(url)
                .then()
                .spec(this.resSpec)
                .extract()
                .response();

        super.log("DELETE");
        resetRequest();
        return response;
    }

    protected void resetRequest() {
        this.body = null;
        this.cookies.clear();
        this.cookie = "";
        this.url = null;
        this.contentType = null;
        this.headers.clear();
        this.pathParams.clear();
        this.queryParams.clear();
        this.formParams.clear();
        this.token = null;
    }

    protected void esperar(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
