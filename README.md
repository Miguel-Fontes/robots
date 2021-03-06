# Robots

<!-- TOC -->

- [Robots](#robots)
    - [Status](#status)
    - [Instruções](#instruções)
        - [Build](#build)
        - [Execução](#execução)
        - [Testes](#testes)
    - [O Desafio](#o-desafio)
        - [Requisitos](#requisitos)
        - [Cenários de Teste](#cenários-de-teste)
        - [Requisitos técnicos](#requisitos-técnicos)

<!-- /TOC -->

## Status
- Master: [![Build Status](https://travis-ci.org/Miguel-Fontes/robots.svg?branch=master)](https://travis-ci.org/Miguel-Fontes/robots)
- Dev: [![Build Status](https://travis-ci.org/Miguel-Fontes/robots.svg?branch=dev)](https://travis-ci.org/Miguel-Fontes/robots)

## Instruções
### Build
Utilize o maven para construir os artefatos da aplicação.

    mvn install
    
### Execução
É possível executar a aplicação diretamente através do jar robots-rest-api.jar.

    java -jar robots-rest-api/target/robots-rest-api-<versao>.jar

Outra opção é executar o script `run.sh`, presente também no diretório target do módulo `robots-rest-api`. Considerando que o diretório atual é o raiz do projeto (`/robots`):

    cd robots-rest-api/target/ && chmod +x ./run.sh && ./run.sh
    
Para o funcionamento do Script, garanta: 
- O mesmo possui direito de execução como aplicação (`chmod +x`)
- O diretório de trabalho atual (pwd) é o em que o script se encontra
- Que o JAR da aplicação esteja no mesmo diretório do script

A aplicação ficará disponível na porta 8080. Utilize `CTRL + C` para encerrá-la.

### Testes
O mesmo script run pode executar testes na aplicação, baseados nos cenários de teste descritos na seção [Cenários de Teste](#cenários-de-teste). Execute-o passando o argumento `test`

    ./run.sh test

Lembre-se de que o diretório de trabalho atual deve ser o diretório atual do script (veja seção anterior [Execução](#execução)). A aplicação será inicializada, e então os testes serão executados, gerando saída similar à:

    > EXECUTING APPLICATION TESTS ----------------------------------------------------------------------
    > 1. Movimento com rotações para direita
    - Saída esperada: HTTP 200; (2, 0, S)
    Output: {"robotFinalPosition":"(2, 0, S)"}
    
    > 2. Movimento para esquerda
    - Saída esperada: HTTP 200; (0, 2, W)
    Output: {"robotFinalPosition":"(0, 2, W)"}
    
    > 3. Repetição da requisição com movimento para esquerda
    - Saída esperada: HTTP200; (0, 2, W)
    Output: {"robotFinalPosition":"(0, 2, W)"}
    .
    .
    .

### Release
O release da aplicação é efetuado utilizando o maven Release Plugin.

    mvn release:prepare
    mvn release:perform
    
Após o push da tag de versão gerada pelo Maven Release Plugin para o repositório: 

- O Travis CI irá buildar o artefato
- Executar os testes
- Caso os testes passem, um novo release no [Github Releases](https://github.com/Miguel-Fontes/robots/releases) será criado.

É possível gerar um `release beta` criando um tag em qualquer commit com a nomenclatura `robots-(beta|alpha|pre)-identificador`, e o mesmo processo de release citado acima será iniciado no Travis CI. Exemplo: `robots-beta-1.3`.

No arquivo `tar.gz` estarão o `jar` executável e o arquivo `run.sh` citados nas seções anteriores ([Execução](#execução)).

## O Desafio
Um time de robôs devem ser colocados pela NASA para explorar um terreno em Marte.
Esse terreno, que é retangular, precisa ser navegado pelos robôs de tal forma que suas câmeras acopladas possam obter uma visão completa da região, enviando essas imagens novamente para a Terra.

A posição de cada robô é representada pela combinação de coordenadas cartesianas (x, y) e por uma letra, que pode representar uma das quatro orientações: NORTH, SOUTH, EAST e WEST. Para simplificar a navegação, a região “marciana” a ser explorada foi subdividia em sub-regiões retangulares.
Uma posição válida de um robô, seria (0, 0, N), que significa que o robô está posicionado no canto esquerdo inferior do terreno, voltado para o Norte.
Para controlar cada robô, a NASA envia uma string simples, que pode conter as letras ‘L’, ‘R’ e ‘M’. As letras ‘L’ e ‘R’ fazem o robô rotacionar em seu próprio eixo 90 graus para esquerda ou para direita, respectivamente, sem se mover da sua posição atual. A letra ‘M’ faz o robô deslocar-se uma posição para frente.
Assuma que um robô se movimenta para o NORTE em relação ao eixo y. Ou seja, um passo para o NORTE da posição (x,y), é a posição (x, y+1)
Exemplo: Se o robô está na posição (0,0,N), o comando "MML" fará ele chegar na posição (0,2,W)

Escreva um programa que permita aos engenheiros da NASA enviar comandos para o Robô e saber onde ele se encontra. Os engenheiros irão rodar testes no seu software para garantir que ele se comporta da forma esperada, antes de enviar o Robô para marte.

### Requisitos
- O terreno deverá ser iniciado com 5x5 posições;
- O robô inicia na coordenada (0,0,N);
- Deverá ser possível enviar um comando para o Robô que me retorne a posição final dele;
- O Robô não pode se movimentar para fora da área especificada;
- Não deve guardar estado do robô para consulta posterior;

### Cenários de Teste
| Descrição | Call | Esperado |
| --------- | ---- | -------- |
Movimento com rotações para direita | curl -s --request POST http://localhost:8080/rest/mars/MMRMMRMM | (2, 0, S)
Movimento para esquerda |  curl -s --request POST http://localhost:8080/rest/mars/MML | (0, 2, W)
Repetição da requisição com movimento para esquerda | curl -s --request POST http://localhost:8080/rest/mars/MML | (0, 2, W) 
Comando inválido | curl -s --request POST http://localhost:8080/rest/mars/AAA |400 Bad Request
Posição inválida | curl -s --request POST http://localhost:8080/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM |400 Bad Request

### Requisitos técnicos
- Deve ter teste
- O desafio deve ser entregue escrito utilizando Java 8;
- O projeto deverá ser compilado utilizando o Maven;
- Deverão ser utilizadas apenas as biblioteca do SpringBoot e JUnit;
- O desafio será executado como uma aplicação SpringBoot;
- A interface de comunicação com o robô é REST;