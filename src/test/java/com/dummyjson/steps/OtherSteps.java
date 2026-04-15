package com.dummyjson.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import com.dummyjson.core.utils.JsonUtils;
import com.dummyjson.data.models.Authentication;
import com.dummyjson.data.models.responses.LoginResponse;
import com.dummyjson.data.models.responses.TestResponse;
import com.dummyjson.data.models.responses.UserResponse;
import com.dummyjson.logic.CommonMethods;
import com.dummyjson.services.AuthProductsService;
import com.dummyjson.services.LoginService;
import com.dummyjson.services.TestService;
import com.dummyjson.services.UsersService;

public class OtherSteps {

    private Response authResponse;

    @And("o status code do login eh {int}")
    public void oStatusCodeDoLoginEh(int code) {
        LoginService testService = new LoginService();
        authResponse = testService.postLogin("emilys", "emilyspass");
        CommonMethods.validateStatusCode(authResponse, code);
    }

    @When("o usuario acessa os produtos com o token recebido")
    public void oUsuarioAcessaOsProdutosComOTokenRecebido() {
        LoginResponse loginResponse = JsonUtils.getDTO(LoginResponse.class, authResponse.asString());
        Authentication authentication = Authentication.builder().token(loginResponse.getRefreshToken()).build();
        AuthProductsService authProductsService = new AuthProductsService();
        SharedContext.setResponse(authProductsService.getProducts(authentication));
    }

    @Given("que o usuario chama o endpoint de teste")
    public void queOUsuarioChamaOEndpointDeTeste() {
        TestService testService = new TestService();
        SharedContext.setResponse(testService.getTest());
    }

    @And("o status do endpoint de teste deve ser {string}")
    public void oStatusDoEndpointDeTesteDeveSer(String status) {
        TestResponse testResponse = JsonUtils.getDTO(TestResponse.class, SharedContext.getResponse().asString());
        CommonMethods.assertThat(
                "Unexpected status was received.",
                "Status is OK",
                testResponse.getStatus(), equalTo(status));
    }

    @And("o metodo do endpoint de teste deve ser {string}")
    public void oMetodoDoEndpointDeTesteDeveSer(String method) {
        TestResponse testResponse = JsonUtils.getDTO(TestResponse.class, SharedContext.getResponse().asString());
        CommonMethods.assertThat(
                "Unexpected method was received.",
                "Method is GET",
                testResponse.getMethod(), equalTo(method));
    }

    @Given("que o usuario chama o servico de usuarios")
    public void queOUsuarioChamaOServicoDeUsuarios() {
        UsersService testService = new UsersService();
        SharedContext.setResponse(testService.getUsers());
    }

    @And("a resposta do primeiro usuario deve conter o nome {string} e o sobrenome {string}")
    public void aRespostaDoPrimeiroUsuarioDeveConterONomeEOSobrenome(String firstName, String lastName) {
        UserResponse userResponse = JsonUtils.getDTO(UserResponse.class, SharedContext.getResponse().asString());

        CommonMethods.assertThat(
                "Unexpected first name was received.",
                "First name is correct.",
                userResponse.getUsers().get(0).getFirstName(), equalTo(firstName));

        CommonMethods.assertThat(
                "Unexpected last name was received.",
                "Last name is correct.",
                userResponse.getUsers().get(0).getLastName(), equalTo(lastName));
    }

}
