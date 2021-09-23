package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.db.DataBase;
import ru.netology.page.DebitCardPaymentPage;
import ru.netology.page.TitlePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitCardPaymentTest {
    private final DebitCardPaymentPage debitCard = new DebitCardPaymentPage();
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
        titlePage.debitCardPayment();
        var validDebitCard = DataGenerator.CardInfo.getValidCardPayment();
        debitCard.validFillFieldDebitCard(validDebitCard);
        debitCard.approvedMessage();
        assertEquals("APPROVED", DataBase.getDebitCardTransactionStatus());
    }

    // Позитивный тест: Номер карты со статусом: "DECLINED";
    @Test
    @Order(2)
    void shouldGetErrorIfCardNumberWithRejectedNumber() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardWithRejectedNumber();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorBankRefusalMessage();
        assertEquals("DECLINED", DataBase.getDebitCardTransactionStatus());
        assertEquals("null", DataBase.getOrderTransactionDebitCard());
    }

    // Негативный тест: Поле 'Номер карты' заполнено невалидными данными;
    @Test
    @Order(3)
    void shouldGetErrorIfInvalidCardNumber() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getInvalidCardNumber();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorBankRefusalMessage();
    }

    // Негативный тест: Поле 'Номер карты' состоит из цифр и букв;
    @Test
    @Order(4)
    void shouldGetErrorIfCardNumberWithWordInNumber() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardNumberWithWordInNumber();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' пустое;
    @Test
    @Order(5)
    void shouldGetErrorIfCardNumberFieldIsEmpty() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardNumberFieldEmpty();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' состоит из букв;
    @Test
    @Order(6)
    void shouldGetErrorIfCardNumberFieldConsistsLetters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardNumberFieldConsistsOfLetters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' состоит из спецсимволов;
    @Test
    @Order(7)
    void shouldGetErrorIfCardNumberFieldWithSpecialCharacters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardNumberFieldWithSpecialCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' состоит из одного символа;
    @Test
    @Order(8)
    void shouldGetErrorIfCardNumberFieldConsistsOneCharacters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardNumberFieldConsistsOneCharacter();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

// Проверка поля "Месяц";

    // Негативный тест: Поле 'Месяц' заполнено невалидными данными;
    @Test
    @Order(9)
    void shouldGetErrorIfInvalidMonthField() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getInvalidMonthField();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageByIncorrectFormatMonthField();
    }

    // Негативный тест: Поле 'Месяц' состоит из спецсимволов;
    @Test
    @Order(10)
    void shouldGetErrorIfMonthFieldWithSpecialCharacters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getMonthFieldWithSpecialCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Месяц' пустое;
    @Test
    @Order(11)
    void shouldGetErrorIfMonthFieldEmpty() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getMonthFieldEmpty();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Месяц' состоит из одного символа;
    @Test
    @Order(12)
    void shouldGetErrorIfMonthFieldConsistsOneCharacters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getMonthFieldOneCharacter();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Месяц' состоит из букв;
    @Test
    @Order(13)
    void shouldGetErrorIfMonthFieldConsistsOfLetters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getMonthFieldConsistsOfLetters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

// Проверка поля "Год";

    // Негативный тест: Поле 'Год' заполнено невалидными данными;
    @Test
    @Order(14)
    void shouldGetErrorIfInvalidYearField() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getInvalidYearField();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageByIncorrectFormatYearField();
    }

    // Негативный тест: Поле 'Год' состоит из спецсимволов;
    @Test
    @Order(15)
    void shouldGetErrorIfYearFieldWithSpecialCharacters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getYearFieldWithSpecialCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Год' пустое;
    @Test
    @Order(16)
    void shouldGetErrorIfYearFieldEmpty() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getYearFieldEmpty();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Год' состоит из одного символа;
    @Test
    @Order(17)
    void shouldGetErrorIfYearFieldConsistsOneCharacters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getYearFieldOneCharacter();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Год' состоит из букв;
    @Test
    @Order(18)
    void shouldGetErrorIfYearFieldConsistsOfLetters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getYearFieldConsistsOfLetters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

// Проверка поля "Владелец";

    // Негативный тест: Поле 'Владелец' состоит из букв и цифр;
    @Test
    @Order(19)
    void shouldGetErrorIfInvalidOwnerField() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldWithLettersAndNumbers();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Владелец' состоит из спецсимволов;
    @Test
    @Order(20)
    void shouldGetErrorIfOwnerFieldWithSpecialCharacters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldWithSpecialCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Владелец' пустое;
    @Test
    @Order(21)
    void shouldGetErrorIfOwnerFieldEmpty() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldEmpty();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageEmptyField();
    }

    // Негативный тест: Поле 'Владелец' состоит из одного символа;
    @Test
    @Order(22)
    void shouldGetErrorIfOwnerFieldConsistsOneCharacters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldConsistsOneCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Владелец' состоит из двойной фамилии;
    @Test
    @Order(23)
    void shouldGetErrorIfOwnerFieldWithDoubleSurname() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldWithDoubleSurname();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.approvedMessage();
    }

    // Негативный тест: Поле 'Владелец' заполнено на кириллице;
    @Test
    @Order(24)
    void shouldGetErrorIfOwnerFieldInCyrillic() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldInCyrillic();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Владелец' состоит из букв разного регистра;
    @Test
    @Order(25)
    void shouldGetErrorIfOwnerFieldConsistsOfLetters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldConsistsOfLettersDifferentRegister();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Проверка поля 'Владелец' на параметр maxLength;
    @Test
    @Order(26)
    void shouldGetErrorIfOwnerFieldWithMaxLength() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldWithMaxLength();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

// Проверка поля "CVC";

    // Негативный тест: Поле 'CVC' заполнено невалидными данными;
    @Test
    @Order(27)
    void shouldGetErrorIfInvalidCVCField() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getInvalidCVCField();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' состоит из спецсимволов;
    @Test
    @Order(28)
    void shouldGetErrorIfCVCFieldWithSpecialCharacters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCVCFieldWithSpecialCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' пустое;
    @Test
    @Order(29)
    void shouldGetErrorIfCVCFieldEmpty() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCVCFieldEmpty();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' состоит из одного символа;
    @Test
    @Order(30)
    void shouldGetErrorIfCVCFieldConsistsOneCharacters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCVCFieldConsistsOneCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' состоит из букв;
    @Test
    @Order(31)
    void shouldGetErrorIfCVCFieldConsistsOfLetters() {
        titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCVCFieldConsistsOfLetters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }
}