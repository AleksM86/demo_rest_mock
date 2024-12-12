# Основа для REST заглушки 
Путь для отправления запросов: http://localhost:8080/info/postBalances

Пример тела запроса:  
```json
{
        "rqUID": "58dgtf565j8547f64ke7",
        "clientId": "9050000000000000000",
        "account": "80500000000000000001",
        "openDate": "2020-01-01",
        "closeDate": "2025-01-01"
        }
```
Пример тела ответа:  
```json
{
    "rqUID": "58dgtf565j8547f64ke7",
    "clientId": "9050000000000000000",
    "account": "80500000000000000001",
    "currency": "EU",
    "balance": 146.02,
    "maxLimit": 1000
}
```
Пример тела запроса и ответа из Postman:
<img src="img.png" alt="Пример из Postman">