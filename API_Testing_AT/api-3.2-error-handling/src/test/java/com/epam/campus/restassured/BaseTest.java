package com.epam.campus.restassured;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BaseTest {
    protected WireMockServer wireMockServer;
    protected int port;
    protected String baseUri;

    @BeforeClass
    public void beforeClass() {
        wireMockServer = new WireMockServer(options()
                .dynamicPort() 
                .usingFilesUnderDirectory("src/test/resources")
        );

        wireMockServer.start();
        System.out.println("Started");
        port = wireMockServer.port();
        baseUri = String.format("http://localhost:%s", port);
    }

    @BeforeMethod
    public void setupRestAssured() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        RestAssured.filters(
                new RequestLoggingFilter(LogDetail.ALL),
                new ResponseLoggingFilter(LogDetail.ALL)
        );
    }

    @AfterClass
    public void afterClass() {
        wireMockServer.stop();
    }
}
