# Запуск автотестов
### Программы:
* IntelliJ IDEA
* Docker Desktop
* Git
* Google Chrome (или любой другой браузер)
### Шаги:
- Клонируем [проект](https://github.com/Mapkuca/diplom.git)
- Открыть проект в **IntelliJ IDEA**
- Выполнить команду в терминале **docker-compose up -d**
### Запуск автотестов с поддержкой MySQL
- Выполнить команду в терминале **`java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -jar aqa-shop.jar`**
- Выполнить команду в терминале для запуска тестов **`gradlew clean test -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -Dspring.datasource.username=app -Dspring.datasource.password=pass`**
- Для запуска определенного теста, выполнить команду в терминале **`gradlew clean test --tests <имя тестового класса> -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -Dspring.datasource.username=app -Dspring.datasource.password=pass`**
### Запуск автотестов с поддержкой PostgreSQL
- Выполнить команду в терминале **`java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -jar aqa-shop.jar`**
- Выполнить команду в терминале для запуска тестов **`gradlew clean test -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -Dspring.datasource.username=app -Dspring.datasource.password=pass`**
- Для запуска определенного теста, выполнить команду в терминале **`gradlew clean test --tests <имя тестового класса> -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -Dspring.datasource.username=app -Dspring.datasource.password=pass`**
### Просмотр отчетов
- Отчет о тестировании автоматически формируется после запуска автотестов и доступен для просмотра в файле **<имя проекта>/build/reports/tests/index.html**
### Завершение работы
- В терминале нажать **`Ctrl+C`**
- Выполнить команду в терминале **`docker-compose down`**