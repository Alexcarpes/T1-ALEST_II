## Trabalho 1 - Algoritmo e Estrutura de Dados II 
## Professor Henry Cabral Nunes

    #Autor: Alexsander Carpes Nunes
    #Atividade: Pennywise e as 10 Crianças Mais Covardes

## Implementação e estrutura:
    -- Com a descrição da atividade via Moodle, a ideia principal é fazer uso
    de uma Fila de Prioridade HeapSort, para evitar carregamento dos arquivos na memória
    1. A variante apropriada: Max-Heap;
    2. Switch-Case para alternar entre os comandos que o usuário pode usar no terminal com Scanner;
    3.

## Código feito em Java em arquivo principal
 ```
 PennywiseCLI.java
 ```

 ## Para compilar o código, vai ser preciso o JDK 21 ou jDK24 (usando o JDK 17 gerou erros de versão, ao testar em JDK24 ele o superou ), usando o JDK 21:
 ```
 javac PennywiseCLI.java
 ```
    -- Após compilar ele carrega e começa a fazer a leitura dos arquivos e retorna uma fila.
    -- Por padrão, o terminal digita o "Pennywise> " abaixo, mas para ficar fácil identificar:

## Iniciando o terminal/programa:
```
Java PennywiseCLI.java
```

## Para utilizar os comandos disponíveis do programas, basta usar (sem o Pennywise>):
```
    Pennywise> consultar regioes/teste.txt
    Pennywise> mostrar
    Pennywise> limpar
    Pennywise> sair
```
