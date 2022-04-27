# language: pt

Funcionalidade: Apenas usuários cadastrados podem se logar

  Cenário: Um usuário inválido não consegue se logar
    Dado o usuário inválido
    Quando tenta se logar
    Então continua na página de login