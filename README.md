# API для работы с кошельками

## Эндпоинты
### 1 Получения баланса кошелька

### Эндпоинт
GET /api/v1/wallets/{WALLET_UUID}

WALLET_UUID - Уникальный индификатор кошелька

### Ответ
Статус: 200 ОК

Тело: WalletDTO

{
"id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"balance": 1000.00
}

### Описание
Возвращает текущий баланс кошелька

### 2 Выполнение операции с кошельком

### Эндпоинт
POST /api/v1/wallet

### Описание
Выполняет операцию пополнения или списания средств.

### Запрос

Заголовки:

Content-Type: application/json

Тело: WalletOperationRequest

{
"walletId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"operationType": "DEPOSIT",
"amount": 50.00
}

Типы операций

DEPOSIT - Пополнение кошелька
WITHDRAW - Списание средств

### Ответ

Статус: 200 OK

Тело: WalletResponse

{
"walletId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"balance": 150.00
}

### Возможные ошибки

400 Bad Request - INVALID UUID FORMAT

400 Bad Request  - Amount: Amount must be greater than 0

400 Bad Request  - Insufficient_funds

404 Not Found - Wallet not found

### Интерактивная документация

Для тестирования API доступен Swagger UI:
http://localhost:8080/swagger-ui.html