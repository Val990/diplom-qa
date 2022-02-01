# Дипломный проект профессии «Тестировщик»
Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Автоматизация
### Процедура запуска авто-тестов 
1) скачать репозиторий командой **git clone**
2) запустить docker контейнеры командой **docker-compose up -d**
3) запустить sut:

3.1 для mysql:  **java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar**

3.2 для postgresql:  **java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar**

4) запустить тесты:

4.1 для mysql:  **./gradlew clean test -Dselenide.headless=true -Ddb.url=jdbc:mysql://localhost:3306/app**

4.2 для postgresql:  **./gradlew clean test -Dselenide.headless=true -Ddb.url=jdbc:postgresql://localhost:5432/app**

5) сгенерировать отчеты командой **./gradlew allureServe**
6) завершить работу sut: **Ctrl+C**
7) завершить работу контейнеров: **docker-compose down**