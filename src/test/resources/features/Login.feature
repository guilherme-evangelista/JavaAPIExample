#language: pt
@login @regression
Funcionalidade: API de Login

  Cenario: Login com sucesso com credenciais validas
    Dado que o usuario chama o servico de login com "emilys" e "emilyspass"
    Entao o status code deve ser 200
    E a resposta de login deve conter o nome "Emily" e o sobrenome "Johnson"
    E a resposta de login deve conter um token de atualizacao valido
