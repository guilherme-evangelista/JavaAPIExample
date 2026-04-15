#language: pt
@authproducts @regression
Funcionalidade: API de Produtos Autenticada

  Cenario: Obter produtos com token de autenticacao valido
    Dado que o usuario chama o servico de login com "emilys" e "emilyspass"
    E o status code do login eh 200
    Quando o usuario acessa os produtos com o token recebido
    Entao o status code deve ser 200
    E o titulo do primeiro produto deve ser "Essence Mascara Lash Princess"
