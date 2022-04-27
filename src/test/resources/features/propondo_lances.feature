# language: pt

Funcionalidade: Propondo lances ao leilão

Cenário: Propondo um único lance válido
 Dado um lance válido
 Quando propõe ao leilão
 Então o lance é aceito
 
Cenário: Propondo vários lances válidos
 Dado um lance de 10.0 reais do usuário "fulano"
 E um lance de 15.0 reais do usuário "beltrano"
 Quando propõe vários lances ao leilão
 Então os lances são aceitos
 
Esquema do Cenário: Propondo um lance inválido
 Dado um lance inválido de <valor> reais e do usuário '<nomeUsuario>'
 Quando propõe ao leilão
 Então o lance não é aceito
 
 Exemplos:
  | valor | nomeUsuario |
  |    0  |    fulano   |
  |   -1  |   beltrano  |

Cenário: Propondo uma sequência de lances
 Dado dois lances
  | valor | nomeUsuario |
  | 10.0  |   beltrano  |
  | 15.0  |   beltrano  |
 Quando propõe vários lances ao leilão
 Então o segundo lance não é aceito