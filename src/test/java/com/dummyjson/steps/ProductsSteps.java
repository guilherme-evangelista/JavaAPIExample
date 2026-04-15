package com.dummyjson.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import com.dummyjson.core.utils.JsonUtils;
import com.dummyjson.data.models.products.Product;
import com.dummyjson.data.models.responses.ProductResponse;
import com.dummyjson.data.models.responses.ProductsResponse;
import com.dummyjson.logic.CommonMethods;
import com.dummyjson.services.ProductsService;

public class ProductsSteps {

    private Response fetchResponse;
    private ProductResponse productResponse;
    private ProductsService productsService = new ProductsService();

    @Given("que o usuario chama o servico de produtos para obter produtos")
    public void queOUsuarioChamaOServicoDeProdutosParaObterProdutos() {
        SharedContext.setResponse(productsService.getProducts());
    }

    @Given("que o usuario chama o servico de produtos para criar um produto {string}")
    public void queOUsuarioChamaOServicoDeProdutosParaCriarUmProduto(String title) {
        SharedContext.setResponse(productsService.postProduct(productCreation(title)));
    }

    @Given("que o usuario chama o servico de produtos para obter o produto com ID {int}")
    public void queOUsuarioChamaOServicoDeProdutosParaObterOProdutoComID(int id) {
        SharedContext.setResponse(productsService.getProductById(id));
    }

    @And("o titulo do primeiro produto deve ser {string}")
    public void oTituloDoPrimeiroProdutoDeveSer(String title) {
        ProductsResponse productsResponse = JsonUtils.getDTO(ProductsResponse.class, SharedContext.getResponse().asString());
        CommonMethods.assertThat(
                "Unexpected product Title was received.",
                "Product Title is correct.",
                productsResponse.getProducts().get(0).getTitle(),
                equalTo(title)
        );
    }

    @And("a resposta do produto deve ter um ID valido")
    public void aRespostaDoProdutoDeveTerUmIDValido() {
        productResponse = JsonUtils.getDTO(ProductResponse.class, SharedContext.getResponse().asString());
        CommonMethods.assertThat(
                "Unexpected product id was received.",
                "Product Id is not null.",
                productResponse.getId() > 0,
                equalTo(true)
        );
    }

    @And("o ID na resposta do produto deve ser {int}")
    public void oIDNaRespostaDoProdutoDeveSer(int id) {
        productResponse = JsonUtils.getDTO(ProductResponse.class, SharedContext.getResponse().asString());
        CommonMethods.assertThat(
                "Unexpected product id was received.",
                "Product Id is correct.",
                productResponse.getId(),
                equalTo((long) id)
        );
    }

    @And("o titulo na resposta do produto deve ser {string}")
    public void oTituloNaRespostaDoProdutoDeveSer(String title) {
        CommonMethods.assertThat(
                "Unexpected product Title was received.",
                "Product Title is correct.",
                productResponse.getTitle(),
                equalTo(title)
        );
    }

    @And("a descricao na resposta do produto deve ser {string}")
    public void aDescricaoNaRespostaDoProdutoDeveSer(String desc) {
        CommonMethods.assertThat(
                "Unexpected product Description was received.",
                "Product Description is correct.",
                productResponse.getDescription(),
                equalTo(desc)
        );
    }

    @When("o usuario busca o produto criado pelo ID")
    public void oUsuarioBuscaOProdutoCriadoPeloID() {
        productResponse = JsonUtils.getDTO(ProductResponse.class, SharedContext.getResponse().asString());
        fetchResponse = productsService.getProductById(productResponse.getId());
    }

    @Then("o status code da busca deve ser {int}")
    public void oStatusCodeDaBuscaDeveSer(int code) {
        CommonMethods.validateStatusCode(fetchResponse, code);
    }

    @And("o ID do produto buscado deve corresponder ao ID do produto criado")
    public void oIDDoProdutoBuscadoDeveCorresponderAoIDDoProdutoCriado() {
        ProductResponse fetchedProduct = JsonUtils.getDTO(ProductResponse.class, fetchResponse.asString());
        CommonMethods.assertThat(
                "Unexpected product id was received.",
                "Product Id is correct.",
                fetchedProduct.getId(),
                equalTo(productResponse.getId())
        );
    }

    @And("o titulo do produto buscado deve ser {string}")
    public void oTituloDoProdutoBuscadoDeveSer(String title) {
        ProductResponse fetchedProduct = JsonUtils.getDTO(ProductResponse.class, fetchResponse.asString());
        CommonMethods.assertThat(
                "Unexpected product Title was received.",
                "Product Title is correct.",
                fetchedProduct.getTitle(),
                equalTo(title)
        );
    }

    @And("a descricao do produto buscado deve ser {string}")
    public void aDescricaoDoProdutoBuscadoDeveSer(String desc) {
        ProductResponse fetchedProduct = JsonUtils.getDTO(ProductResponse.class, fetchResponse.asString());
        CommonMethods.assertThat(
                "Unexpected product Description was received.",
                "Product Description is correct.",
                fetchedProduct.getDescription(),
                equalTo(desc)
        );
    }

    public Product productCreation(String title) {
        return Product.builder()
                .title(title)
                .description("Mega Discount, Impression of A...")
                .price(13D)
                .discountPercentage(8.4)
                .rating(4.26)
                .stock(65)
                .brand("Impression of Acqua Di Gio")
                .category("fragrances")
                .thumbnail("https://i.dummyjson.com/data/products/11/thumnail.jpg")
                .build();
    }
}
