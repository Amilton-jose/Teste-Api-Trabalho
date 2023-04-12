# Plano de Testes API Serverest

Este documento descreve o plano de testes da API Serverest utilizando Java com a biblioteca RestAssured e o framework JUnit.

## Objetivo

O objetivo deste plano de testes é garantir que a API Serverest esteja funcionando corretamente em relação aos endpoints de login, usuário e produto.

## Configuração

Antes de iniciar os testes, é necessário realizar a configuração da API Serverest e instalar as bibliotecas RestAssured e JUnit.

## Endpoint de Login

### Teste de sucesso no login

1. Verificar se o usuário é autenticado com sucesso.
2. Verificar se o status code retornado é 200 (OK).
3. Verificar se o token de autenticação é retornado corretamente.

### Teste de falha no login

1. Verificar se o usuário não é autenticado com credenciais inválidas.
2. Verificar se o status code retornado é 401 (Unauthorized).
3. Verificar se a mensagem de erro é retornada corretamente.

## Endpoint de Usuário

### Teste de sucesso na criação de usuário

1. Verificar se o usuário é criado com sucesso.
2. Verificar se o status code retornado é 201 (Created).
3. Verificar se o usuário criado possui as informações corretas.

### Teste de sucesso na atualização de usuário

1. Verificar se o usuário é atualizado com sucesso.
2. Verificar se o status code retornado é 200 (OK).
3. Verificar se o usuário atualizado possui as informações corretas.

### Teste de sucesso na exclusão de usuário

1. Verificar se o usuário é excluído com sucesso.
2. Verificar se o status code retornado é 204 (No Content).

## Endpoint de Produto

### Teste de sucesso na criação de produto

1. Verificar se o produto é criado com sucesso.
2. Verificar se o status code retornado é 201 (Created).
3. Verificar se o produto criado possui as informações corretas.

### Teste de sucesso na atualização de produto

1. Verificar se o produto é atualizado com sucesso.
2. Verificar se o status code retornado é 200 (OK).
3. Verificar se o produto atualizado possui as informações corretas.

### Teste de sucesso na exclusão de produto

1. Verificar se o produto é excluído com sucesso.
2. Verificar se o status code retornado é 204 (No Content).

## Conclusão

Este plano de testes foi criado com o objetivo de garantir o funcionamento correto da API Serverest em relação aos endpoints de login, usuário e produto. É importante lembrar que este plano de testes não cobre todos os possíveis cenários de teste e pode ser expandido para incluir mais casos de teste.
