
## Курсовой проект профессии «Тестировщик»
Курсовой проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

### **Инструкция по запуску**
**Предварительные условия:**
1. Установить [Docker](https://www.docker.com/).
   
   [Руководство по установке Docker](https://github.com/netology-code/aqa-homeworks/blob/master/docker/installation.md)
2. Открыть Intellij IDEA.
   
   [Руководство по установке IntelliJ IDEA](https://github.com/netology-code/javaqa-homeworks/blob/master/intro/idea.md)
3. Создать проект в IDEA на базе Gradle.
4. Склонировать [репозиторий](https://github.com/Ekaterina5885/Course_Work);

### **Для запуска MySQL**

   - **1.** Выполнить в окне Терминала команду: ```docker-compose -f docker-compose-mysql.yml up```
   - **2.** Открыть новую вкладку в окне Терминала.
   - **3.** Выполнить в окне Терминала команду: ```java -jar ./artifacts/aqa-shop.jar```
   - **4.** Выполнить команду:```gradle clean test```
   - **5.** Выполнить команду:```gradle allureReport```
   - **6.** Выполнить команду:```gradle allureServe```
   - **7.** Остановить работу приложения сочетанием клавиш:```Ctrl + C```

### **Для запуска Postgres**

   - **1.** В файле ```build.gradle``` раскомментировать ```41``` строку и закомментировать ```42``` строку;
   - **2.** В файле ```application.properties``` закомментировать ```3``` строку;
   - **3.** Выполнить в окне Терминала команду: ```docker-compose -f docker-compose-postgres.yml up```
   - **4.** Открыть новую вкладку в окне Терминала.
   - **5.** Выполнить в окне Терминала команду: ```java -jar ./artifacts/aqa-shop.jar```
   - **6.** Выполнить команду:```gradle clean test```
   - **7.** Выполнить команду:```gradle allureReport```
   - **8.** Выполнить команду:```gradle allureServe```

   - **9.** Остановить работу приложения сочетанием клавиш:```Ctrl + C```

   - **9.** Остановить работу приложения сочетанием клавиш:```Ctrl + C```

