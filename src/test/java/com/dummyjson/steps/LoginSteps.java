package com.dummyjson.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import com.dummyjson.core.utils.JsonUtils;
import com.dummyjson.data.models.responses.LoginResponse;
import com.dummyjson.logic.CommonMethods;
import com.dummyjson.services.LoginService;

public class LoginSteps {

    private LoginResponse loginResponse;

    @Given("que o usuario chama o servico de login com {string} e {string}")
    public void queOUsuarioChamaOServicoDeLoginComE(String username, String password) {
        LoginService testService = new LoginService();
        SharedContext.setResponse(testService.postLogin(username, password));
    }

    @Then("o status code deve ser {int}")
    public void oStatusCodeDeveSer(Integer statusCode) {
        CommonMethods.validateStatusCode(SharedContext.getResponse(), statusCode);
    }

    @And("a resposta de login deve conter o nome {string} e o sobrenome {string}")
    public void aRespostaDeLoginDeveConterONomeEOSobrenome(String firstName, String lastName) {
        loginResponse = JsonUtils.getDTO(LoginResponse.class, SharedContext.getResponse().asString());

        CommonMethods.assertThat(
                "Unexpected first name was received.",
                "First name is correct.",
                loginResponse.getFirstName(), equalTo(firstName));

        CommonMethods.assertThat(
                "Unexpected last name was received.",
                "Last name is correct.",
                loginResponse.getLastName(), equalTo(lastName));
    }

    @And("a resposta de login deve conter um token de atualizacao valido")
    public void aRespostaDeLoginDeveConterUmTokenDeAtualizacaoValido() {
        CommonMethods.assertThat(
                "Unexpected token was received.",
                "Token is present.",
                loginResponse.getRefreshToken(), not(emptyOrNullString()));
    }
}
