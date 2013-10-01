# SimulaLM - Simulador de Eventos Discretos

## Introdução
 
  A simulação aplicada a fluxos de filas  é uma forma conveniente para  analisar  o comportamento de servidores  por  meio da construção de cenários que relacionam elementos fixos como a capacidade de processamento de um servidor com eventos aleatórios como a chegada de um pacote. 
  O exercício de simulação realizado teve como objetivo simular o comportamento de um servidor e avaliar a relação entre o retardo médio e a utilização conforme aumenta o fluxo médio de chegada de pacotes. 

 
## Cenário de Simulação
  Antes da construção do simulador  foi concebido o cenário a ser simulado e quais os dados, elementos ou características necessários ao cumprimento dos objetivos do processo de análise.  Seguindo  como base  o modelo conceitual de sistema de filas de (Andrade,  1998), foi determinada a caracterização do sistema englobando os seguintes elementos: 

  * Modelo de chegada dos usuários  ao serviço:  foi determinado que o tempo de chegada dos pacotes ao servidor é estocástico com  distribuição exponencial de intervalos de chegada de pacotes.  

  * Modelo de serviço (atendimento aos usuários):  não há perdas de pacotes, o servidor disponibiliza uma fila de tamanho ilimitado. 

  * Número de servidores: 1. 

  * Capacidade do Sistema: capacidade ilimitada (¥). 
 
  * Tamanho da População:  cada simulação trata uma amostra de 1 milhão de pacotes neste cenário, um número aleatório de pacotes chega a cada unidade de tempo t em que o sistema será simulado.  

* Disciplina da Fila: FIFO (first in, first out). 


## Levantamento estatístico
 
Desta forma, para simular o aumento do fluxo de chegada de pacotes  , a média de intervalos de chegada λ é considerada com valores entre 1 e 0,0005, para cada conjunto de rodadas de simulação, onde 1 tenta simular uma rede ociosa e 0,0005 uma rede com grande fluxo de pacotes. A variação dos valores de λ, para cada conjunto de rodadas de simulação, é calculada de forma que,0,0005 <= λ <= 1. Desta forma, na primeira rodada de simulações o valor de λ = 1 e a Cada rodada λ = λ*0.9 até a rodada em que λ <= 0,0005. Sendo assim, o momento de chegada de cada pacote _S_ tal _S_ _R_,,i,, que para uma rodada _i_ de simulações, é dada por x = λ,,i,, ln n onde 1 <= _i_ <= 30. 
 
Tendo como  foco da análise a verificação da razão entre o retardo médio e a utilização do servidor foram executadas  30 rodadas de  simulações para cada valor de média e calculado um valor para esta razão admitindo um IC (Intervalo de Confiança) de 95%. 
 