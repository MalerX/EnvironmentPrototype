# Тестовый стенд с микросервисами и окружением.

Прототип окружения из 3 микросервисов, общающихся между собой через брокер сообщений **Apache Kafka**. Сбор телеметрии
выполняется по стандарту **OpenTelemetry**, библиотекой **OpenTelemetry Instrumentation for Java**, подключённой как _javaagent_.
Так же выполняется автоматический сбор данных из топиков Kafka и бэкап в БД **ClickHouse**, при помощи встроенных
инструментов **СУБД ClickHouse**. Микросервисы и окружение запускаются в индивидуальных Docker-контейнерах под управлением
**Docker-compose**.

Доступ к окружению возможен по адресам:
- Endpoint микросервиса -- **_localhost:8080_**
- Jaeger -- **_localhost:8089_**
- Kafka-UI -- **_localhost:8088_**
- ClickHouse (через clickhouse-client) -- **_localhost:8087_**

Для установки и подключения к СУБД ClickHouse при помощи **clickhouse-client** необходимо выполнить следующие шаги:
- Установить clickhouse-client на хостовую машину: **_sudo apt install clickhouse-client_**
- Выполнить подключение: **_clickhouse-client --host localhost --port 8087_**

В данном репозитории можно найти:
- примеры конфигурационных файлов для Spring Boot приложений с параметрами для запуска и
конфигурации продюсеров/консюмеров Kafka (application.yaml);
- Dockerfile и docker-compose.yaml для конфигурирования и запуска настоящего окружения;
- кодовую базу микросервисов;
- шаблоны комманд для создания таблиц, потребителей и материализованных представлений в СУБД ClickHouse. 