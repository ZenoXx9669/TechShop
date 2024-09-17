# TechShop

TechShop — это REST API для интернет-магазина с поддержкой авторизации пользователей, системы ролей, управления товарами и категории, а также функционала избранных товаров.

## Оглавление

- [Технологии](#технологии)
- [Запуск проекта](#запуск-проекта)
- [Структура проекта](#структура-проекта)
- [API Эндпоинты](#api-эндпоинты)
- [Безопасность](#безопасность)
- [Тестирование](#тестирование)
- [Контакты](#контакты)

## Технологии

- Java 17
- Spring Boot 3.3.2
- Spring Security
- Spring Data JPA
- Spring WEB
- Hibernate
- PostgresSQL
- Liquibase
- MailSender
- Lombok
- MapStruct
- Insomnia
- JUnit 5
- Gradle

## Запуск проекта

1. Клонируйте репозиторий:

   ```bash
   git clone https://github.com/ZenoXx9669/TechShop.git
2. Откройте проект в вашей среде разработки (например, IntelliJ IDEA).

3. Настройте доступ к базе данных в файле application.properties или application.yml:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5433/TechShop
    spring.datasource.username=yourUsername
    spring.datasource.password=yourPassword
    ```
4. Запустите проект
    ```bash
    gradle TechShopApplication:run
5. Приложение будет доступно по адресу: http://localhost:8888. 
## Структура проекта
- auth: содержит классы для управления пользователями, их авторизацией и ролями.
- models: сущности базы данных, такие как Product, Category, User, Owner, Favorite,Payment,TechProfile,Type,Image.
- repositories: интерфейсы для взаимодействия с базой данных.
- service: бизнес-логика приложения, реализация CRUD-операций.
- controllers: REST-контроллеры для обработки HTTP-запросов.
- config: конфигурации Spring Security и другие необходимые настройки.
## API Эндпоинты
### Пользователи
- POST /auth/register — регистрация нового пользователя.
- POST /auth/login — авторизация пользователя. 
### Продукты
- GET /product — получение списка всех продуктов.
- POST /product — добавление нового продукта (только для пользователей с ролью Salesman).
- GET /product/{id} — получение информации о продукте по ID.
- PUT /product — обновление информации о продукте.
- DELETE /product/{id} — удаление продукта.
### Избранное
- POST /favorites/add — добавление продукта в избранное.
- GET /my/favorites/{userId} — получение списка избранных продуктов пользователя.
## Безопасность
  ### Проект использует Spring Security для аутентификации и авторизации. Реализованы роли:

- USER — базовый пользователь.
- SALESMAN — пользователь, имеющий право добавлять товары.
### Для каждой роли предусмотрены различные разрешения на доступ к API.
## Тестирование
### Для тестирования приложения используется JUnit 5. Для выполнения тестов выполните:
```bash
   gradle test
   ```
## Контакты
### Если у вас возникли вопросы или предложения, свяжитесь со мной по электронной почте: [olzhasjakenov@gmail.com](#olzhasjakenov@gmail.com)
