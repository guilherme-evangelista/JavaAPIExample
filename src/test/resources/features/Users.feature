#language: pt
@users @regression
Funcionalidade: API de Usuarios

  Cenario: Validar lista de usuarios
    Dado que o usuario chama o servico de usuarios
    Entao o status code deve ser 200
    E a resposta do primeiro usuario deve conter o nome "Emily" e o sobrenome "Johnson"
