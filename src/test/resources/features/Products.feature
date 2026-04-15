#language: pt
@products @regression
Funcionalidade: API de Produtos
  
  Cenario: Obter todos os produtos
    Dado que o usuario chama o servico de produtos para obter produtos
    Entao o status code deve ser 200
    E o titulo do primeiro produto deve ser "Essence Mascara Lash Princess"

  Cenario: Criar um produto
    Dado que o usuario chama o servico de produtos para criar um produto "Perfume Oil"
    Entao o status code deve ser 201
    E a resposta do produto deve ter um ID valido
    E o titulo na resposta do produto deve ser "Perfume Oil"
    E a descricao na resposta do produto deve ser "Mega Discount, Impression of A..."

  Cenario: Obter produto por ID
    Dado que o usuario chama o servico de produtos para obter o produto com ID 1
    Entao o status code deve ser 200
    E o ID na resposta do produto deve ser 1
    E o titulo na resposta do produto deve ser "Essence Mascara Lash Princess"


