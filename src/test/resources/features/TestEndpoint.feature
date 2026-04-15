#language: pt
@test @regression
Funcionalidade: API do Endpoint de Teste

  Cenario: Validar que o endpoint de teste retorna OK
    Dado que o usuario chama o endpoint de teste
    Entao o status code deve ser 200
    E o status do endpoint de teste deve ser "ok"
    E o metodo do endpoint de teste deve ser "GET"
