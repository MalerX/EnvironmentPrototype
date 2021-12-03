# Тестовый стенд с микросервисами и окружением.

Прототип окружения из 3 микросервисов, общающихся между собой через брокер сообщений **Apache Kafka**. Сбор телеметрии
выполняется по стандарту **OpenTelemetry**, библиотекой **OpenTelemetry Instrumentation for Java**, подключённой как _
javaagent_. Так же выполняется автоматический сбор данных из топиков Kafka и бэкап в БД **ClickHouse**, при помощи
встроенных инструментов **СУБД ClickHouse**. Микросервисы и окружение запускаются в индивидуальных Docker-контейнерах
под управлением
**Docker-compose**.

##### Доступ к окружению возможен по адресам:

- Endpoint микросервиса -- **_localhost:8080_**
- Jaeger -- **_localhost:8089_**
- Kafka-UI -- **_localhost:8088_**
- ClickHouse (через clickhouse-client) -- **_localhost:8087_**

#### Для установки и подключения к СУБД ClickHouse при помощи _clickhouse-client_ необходимо выполнить следующие шаги:

- Установить clickhouse-client на хостовую машину: **_sudo apt install clickhouse-client_**
- Выполнить подключение: **_clickhouse-client --host localhost --port 8087_**

#### В данном репозитории можно найти:

- Конфигурационные файлы для Spring Boot приложений с параметрами для создания и запуска продюсеров/консюмеров Kafka (
  _application.yaml_);
- Конфигурационные файлы для создания, настройки и запуска настоящего окружения (_Dockerfile_ и *docker-compose.yam*l);
- кодовую базу микросервисов;
- шаблоны комманд для создания таблиц, потребителей и материализованных представлений в СУБД ClickHouse (
  _ClickHouseGenerationTablesScript.txt_). 