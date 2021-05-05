# Api Calcula Rota:

Esta api foi desenvolvida para calcular o menor valor de entrega por rota para o cliente.

É uma API RESTful construída com Java 11 + Spring Boot + MySQL + Algoritmo de Dijkstra.

O principal objetivo desta API é calcular o menor valor de entrega por rota para o cliente através do algoritmo de Dijkstra.

## Começando

### Pré-requisitos

Para executar este projeto em modo de desenvolvimento, você precisará ter um ambiente básico com Java JDK 11+ 
e Maven 3.5.4+ instalado. Para usar o banco de dados, você precisará ter o MySQL instalado e em execução na sua máquina na porta padrão e criar um database 
com o nome **calcularota**.



### Instalando

**Clonando o Repositório**
```
$ git clone https://github.com/jaquecruvinel/api-calcula-rota.git
$ cd api-calcula-rota
```
### Executando o ambiente de desenvolvimento

**Executando com Maven**

**API em execução**

```
$ mvn spring-boot:run
```

Para executar os testes, use o seguinte comando

**Executando os testes**

```
$ mvn clean test
```

### Consumindo a API

**Criar uma malha**
```
$curl --location --request POST 'localhost:8080/v1/malhas' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=DC416665BAA40418F5777472DFFD79DB' \
--data-raw '{
    "origem": "A",
    "destino": "B",
    "distancia": 30
}'
```
Código de status de resposta: **201**
```
$ {
    "origem": "A",
    "destino": "B",
    "distancia": 30
}'

```
**Buscar malha por ID**

```
$ curl --location --request GET 'http: // localhost: 8080 / v1 / malhas / {id}'
```
Código de status de resposta **200**
```
$ {"id": 1,
    "origem": "A",
    "destino": "B",
    "distancia": 30
}'
```

**Listar malha paginado**
```
$curl --location --request GET 'http://localhost:8080/v1/malhas?page=0&size=10' \
--header 'Cookie: JSESSIONID=DC416665BAA40418F5777472DFFD79DB'
```
Código de status de resposta **200**
```
$ {
    "content": [
        {
            "id": 2,
            "origem": "A",
            "destino": "B",
            "distancia": 30.00
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 10,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
}
```
**Excluir malha por ID**
```
$curl --location --request DELETE 'http://localhost:8080/v1/malhas/2' \
--header 'Cookie: JSESSIONID=DC416665BAA40418F5777472DFFD79DB'
```
Código de status de resposta **204**
