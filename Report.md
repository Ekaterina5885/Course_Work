##**Отчёт о проведённом тестировании**

Проведено тестирование работы веб-сервиса по покупке тура, с помощью автоматизированных тестов.

В ходе тестирования, было проверено два варианта оплаты:

  - **1.** **Оплата по дебетовой карте;**

  - **2.** **Оплата с помощью выдачи кредита по данным банковской карты;**

Проверена поддержка двух СУБД:

  **1. MySQL;**

  **2. PostgreSQL;**

### **Количество тест-кейсов**

**Всего: 62 авто-теста:**

   - **31 авто-тест** - для оплаты по дебетовой карте;
    
   - **31 авто-тест** - для оплаты с помощью выдачи кредита по данным банковской карты;

**Успешных: 46 (74.19%);**

**Не успешных: 16 (25.81%);**

### **Отчеты по результатам тестирования:**

[Отчет по результатам тестирования - Gradle]()

[Отчет по результатам тестирования - Allure]()

По результату тестирования, были выявлены баги, о чем заведены баг-репорты 
с описанием выявленных дефектов и оформлены в разделе [Issues]()

**Общие рекомендации**

Необходимо устранить все найденные ошибки для корректной работы веб-сервиса.