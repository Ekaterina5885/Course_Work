package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.db.DataBase;
import ru.netology.page.CreditCardPaymentPage;
import ru.netology.page.TitlePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardPaymentTest {

    private final CreditCardPaymentPage creditCard = new CreditCardPaymentPage();
    private final TitlePage titlePage = new TitlePage();

    @BeforeEach
    public void setOpen() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

// Проверка поля "Номер карты";

    // Позитивный тест: Номер карты со статусом: "APPROVED";
    @Test
    @Order(1)
    void shouldSendFormSuccessFully() {
        titlePage.creditCardPayment();
        var validCreditCard = DataGenerator.CardInfo.getValidCardPayment();
        creditCard.validFillFieldCreditCard(validCreditCard);
        creditCard.approvedMessage();
        assertEquals("APPROVED", DataBase.getCreditCardTransactionStatus());
    }

    // Позитивный тест: Номер карты со статусом: "DECLINED";
    @Test
    @Order(2)
    void shouldGetErrorIfCardNumberWithRejectedNumber() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getCardWithRejectedNumber();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorBankRefusalMessage();
        assertEquals("DECLINED", DataBase.getCreditCardTransactionStatus());
        assertEquals("null", DataBase.getOrderTransactionCreditCard());
    }

    // Негативный тест: Поле 'Номер карты' заполнено невалидными данными;
    @Test
    @Order(3)
    void shouldGetErrorIfInvalidCardNumber() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getInvalidCardNumber();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorBankRefusalMessage();
    }

    // Негативный тест: Поле 'Номер карты' состоит из цифр и букв;
    @Test
    @Order(4)
    void shouldGetErrorIfCardNumberWithWordInNumber() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getCardNumberWithWordInNumber();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' пустое;
    @Test
    @Order(5)
    void shouldGetErrorIfCardNumberFieldIsEmpty() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getCardNumberFieldEmpty();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' состоит из букв;
    @Test
    @Order(6)
    void shouldGetErrorIfCardNumberFieldConsistsLetters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getCardNumberFieldConsistsOfLetters();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' состоит из спецсимволов;
    @Test
    @Order(7)
    void shouldGetErrorIfCardNumberFieldWithSpecialCharacters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getCardNumberFieldWithSpecialCharacters();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' состоит из одного символа;
    @Test
    @Order(8)
    void shouldGetErrorIfCardNumberFieldConsistsOneCharacters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getCardNumberFieldConsistsOneCharacter();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

// Проверка поля "Месяц";

    // Негативный тест: Поле 'Месяц' заполнено невалидными данными;
    @Test
    @Order(9)
    void shouldGetErrorIfInvalidMonthField() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getInvalidMonthField();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageByIncorrectFormatMonthField();
    }

    // Негативный тест: Поле 'Месяц' состоит из спецсимволов;
    @Test
    @Order(10)
    void shouldGetErrorIfMonthFieldWithSpecialCharacters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getMonthFieldWithSpecialCharacters();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Месяц' пустое;
    @Test
    @Order(11)
    void shouldGetErrorIfMonthFieldEmpty() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getMonthFieldEmpty();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Месяц' состоит из одного символа;
    @Test
    @Order(12)
    void shouldGetErrorIfMonthFieldConsistsOneCharacters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getMonthFieldOneCharacter();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Месяц' состоит из букв;
    @Test
    @Order(13)
    void shouldGetErrorIfMonthFieldConsistsOfLetters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getMonthFieldConsistsOfLetters();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

// Проверка поля "Год";

    // Негативный тест: Поле 'Год' заполнено невалидными данными;
    @Test
    @Order(14)
    void shouldGetErrorIfInvalidYearField() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getInvalidYearField();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageByIncorrectFormatYearField();
    }

    // Негативный тест: Поле 'Год' состоит из спецсимволов;
    @Test
    @Order(15)
    void shouldGetErrorIfYearFieldWithSpecialCharacters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getYearFieldWithSpecialCharacters();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Год' пустое;
    @Test
    @Order(16)
    void shouldGetErrorIfYearFieldEmpty() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getYearFieldEmpty();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Год' состоит из одного символа;
    @Test
    @Order(17)
    void shouldGetErrorIfYearFieldConsistsOneCharacters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getYearFieldOneCharacter();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Год' состоит из букв;
    @Test
    @Order(18)
    void shouldGetErrorIfYearFieldConsistsOfLetters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getYearFieldConsistsOfLetters();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

// Проверка поля "Владелец";

    // Негативный тест: Поле 'Владелец' состоит из букв и цифр;
    @Test
    @Order(19)
    void shouldGetErrorIfOwnerFieldWithLettersAndNumbers() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getOwnerFieldWithLettersAndNumbers();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Владелец' состоит из спецсимволов;
    @Test
    @Order(20)
    void shouldGetErrorIfOwnerFieldWithSpecialCharacters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getOwnerFieldWithSpecialCharacters();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Владелец' пустое;
    @Test
    @Order(21)
    void shouldGetErrorIfOwnerFieldEmpty() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getOwnerFieldEmpty();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageEmptyField();
    }

    // Негативный тест: Поле 'Владелец' состоит из одного символа;
    @Test
    @Order(22)
    void shouldGetErrorIfOwnerFieldConsistsOneCharacters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getOwnerFieldConsistsOneCharacters();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Владелец' состоит из букв разного регистра;
    @Test
    @Order(23)
    void shouldGetErrorIfOwnerFieldConsistsOfLetters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getOwnerFieldConsistsOfLettersDifferentRegister();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Владелец' состоит из двойной фамилии;
    @Test
    @Order(24)
    void shouldGetErrorIfOwnerFieldWithDoubleSurname() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getOwnerFieldWithDoubleSurname();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.approvedMessage();
        assertEquals("APPROVED", DataBase.getCreditCardTransactionStatus());
    }

    // Негативный тест: Поле 'Владелец' заполнено на кириллице;
    @Test
    @Order(25)
    void shouldGetErrorIfOwnerFieldInCyrillic() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getOwnerFieldInCyrillic();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Проверка поля 'Владелец' на параметр maxLength;
    @Test
    @Order(26)
    void shouldGetErrorIfOwnerFieldWithMaxLength() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getOwnerFieldWithMaxLength();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

// Проверка поля "CVC";

    // Негативный тест: Поле 'CVC' заполнено невалидными данными;
    @Test
    @Order(27)
    void shouldGetErrorIfInvalidCVCField() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getInvalidCVCField();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' состоит из спецсимволов;
    @Test
    @Order(28)
    void shouldGetErrorIfCVCFieldWithSpecialCharacters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getCVCFieldWithSpecialCharacters();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' пустое;
    @Test
    @Order(29)
    void shouldGetErrorIfCVCFieldEmpty() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getCVCFieldEmpty();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' состоит из одного символа;
    @Test
    @Order(30)
    void shouldGetErrorIfCVCFieldConsistsOneCharacters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getCVCFieldConsistsOneCharacters();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' состоит из букв;
    @Test
    @Order(31)
    void shouldGetErrorIfCVCFieldConsistsOfLetters() {
        titlePage.creditCardPayment();
        var invalidCreditCard = DataGenerator.CardInfo.getCVCFieldConsistsOfLetters();
        creditCard.invalidFillField(invalidCreditCard);
        creditCard.errorMessageIncorrectFormat();
    }
}