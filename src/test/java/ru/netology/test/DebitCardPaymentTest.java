package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.db.DataBase;
import ru.netology.page.DebitCardPaymentPage;
import ru.netology.page.TitlePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitCardPaymentTest {

    private DebitCardPaymentPage debitCard;
    private TitlePage titlePage;

    @BeforeEach
    public void setOpen() {
        titlePage = open("http://localhost:8080", TitlePage.class);
    }

// Проверка поля "Номер карты";

    // Позитивный тест: Номер карты со статусом: "APPROVED";
    @Test
    @Order(1)
    void shouldSendFormSuccessFully() {
        debitCard = titlePage.debitCardPayment();
        var validDebitCard = DataGenerator.CardInfo.getValidCardPayment();
        debitCard.validFillFieldDebitCard(validDebitCard);
        debitCard.approvedMessage();
        assertEquals("APPROVED", DataBase.getDebitCardTransactionStatus());
    }

    // Негативный тест: Номер карты со статусом: "DECLINED";
    @Test
    @Order(2)
    void shouldGetErrorIfCardNumberWithRejectedNumber() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardWithRejectedNumber();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorBankRefusalMessage();
        assertEquals("DECLINED", DataBase.getDebitCardTransactionStatus());
    }

    // Негативный тест: Поле 'Номер карты' заполнено невалидными данными;
    @Test
    @Order(3)
    void shouldGetErrorIfInvalidCardNumber() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getInvalidCardNumber();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorBankRefusalMessage();
    }

    // Негативный тест: Поле 'Номер карты' состоит из цифр и букв;
    @Test
    @Order(4)
    void shouldGetErrorIfCardNumberWithWordInNumber() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardNumberWithWordInNumber();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' пустое;
    @Test
    @Order(5)
    void shouldGetErrorIfCardNumberFieldIsEmpty() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardNumberFieldEmpty();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' состоит из букв латинского алфавита;
    @Test
    @Order(6)
    void shouldGetErrorIfCardNumberFieldConsistsLetters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardNumberFieldConsistsOfLetters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' состоит из спецсимволов;
    @Test
    @Order(7)
    void shouldGetErrorIfCardNumberFieldWithSpecialCharacters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardNumberFieldWithSpecialCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Номер карты' состоит из одного символа;
    @Test
    @Order(8)
    void shouldGetErrorIfCardNumberFieldConsistsOneCharacters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCardNumberFieldConsistsOneCharacter();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

// Проверка поля "Месяц";

    // Негативный тест: Поле 'Месяц' заполнено несуществующей датой;
    @Test
    @Order(9)
    void shouldGetErrorIfInvalidMonthField() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getInvalidMonthField();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageCardExpirationDateIncorrect();
    }

    // Негативный тест: Поле 'Месяц' состоит из спецсимволов;
    @Test
    @Order(10)
    void shouldGetErrorIfMonthFieldWithSpecialCharacters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getMonthFieldWithSpecialCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Месяц' пустое;
    @Test
    @Order(11)
    void shouldGetErrorIfMonthFieldEmpty() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getMonthFieldEmpty();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Месяц' состоит из одного символа;
    @Test
    @Order(12)
    void shouldGetErrorIfMonthFieldConsistsOneCharacters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getMonthFieldOneCharacter();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Месяц' состоит из букв;
    @Test
    @Order(13)
    void shouldGetErrorIfMonthFieldConsistsOfLetters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getMonthFieldConsistsOfLetters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: В поле 'Месяц' указана дата прошедшего месяца;
    @Test
    @Order(14)
    void shouldGetErrorIfMonthHasExpired() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getLastMonth();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageCardExpirationDateIncorrect();
    }

// Проверка поля "Год";

    // Негативный тест: Поле 'Год' состоит из даты прошедшего года;
    @Test
    @Order(15)
    void shouldGetErrorIfLastYear() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getLastYear();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageByIncorrectFormatYearField();
    }

    // Негативный тест: Поле 'Год' состоит из спецсимволов;
    @Test
    @Order(16)
    void shouldGetErrorIfYearFieldWithSpecialCharacters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getYearFieldWithSpecialCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Год' пустое;
    @Test
    @Order(17)
    void shouldGetErrorIfYearFieldEmpty() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getYearFieldEmpty();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Год' состоит из одного символа;
    @Test
    @Order(18)
    void shouldGetErrorIfYearFieldConsistsOneCharacters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getYearFieldOneCharacter();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Год' состоит из букв;
    @Test
    @Order(19)
    void shouldGetErrorIfYearFieldConsistsOfLetters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getYearFieldConsistsOfLetters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Год' указан срок действия карты, который заканчивается через 6 лет;
    @Test
    @Order(20)
    void shouldGetErrorIfYearHasExpired() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getYearHasExpired();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageCardExpirationDateIncorrect();
    }

    // Позитивный тест: Поле 'Год' указан срок действия карты, который заканчивается через 5 лет;
    @Test
    @Order(21)
    void shouldGetErrorIfYearBeforeExpirationDate() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getYearBeforeExpirationDate();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.approvedMessage();
        assertEquals("APPROVED", DataBase.getDebitCardTransactionStatus());
    }

// Проверка поля "Владелец";

    // Негативный тест: Поле 'Владелец' состоит из букв и цифр;
    @Test
    @Order(22)
    void shouldGetErrorIfInvalidOwnerField() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldWithLettersAndNumbers();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Владелец' состоит из спецсимволов;
    @Test
    @Order(23)
    void shouldGetErrorIfOwnerFieldWithSpecialCharacters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldWithSpecialCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Владелец' пустое;
    @Test
    @Order(24)
    void shouldGetErrorIfOwnerFieldEmpty() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldEmpty();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageEmptyField();
    }

    // Негативный тест: Поле 'Владелец' состоит из одного символа;
    @Test
    @Order(25)
    void shouldGetErrorIfOwnerFieldConsistsOneCharacters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldConsistsOneCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Позитивный тест: Поле 'Владелец' состоит из двойной фамилии;
    @Test
    @Order(26)
    void shouldGetErrorIfOwnerFieldWithDoubleSurname() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldWithDoubleSurname();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.approvedMessage();
        assertEquals("APPROVED", DataBase.getDebitCardTransactionStatus());
    }

    // Негативный тест: Поле 'Владелец' заполнено на кириллице;
    @Test
    @Order(27)
    void shouldGetErrorIfOwnerFieldInCyrillic() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldInCyrillic();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'Владелец' состоит из букв разного регистра;
    @Test
    @Order(28)
    void shouldGetErrorIfOwnerFieldConsistsOfLetters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldConsistsOfLettersDifferentRegister();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Проверка поля 'Владелец' на параметр maxLength;
    @Test
    @Order(29)
    void shouldGetErrorIfOwnerFieldWithMaxLength() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getOwnerFieldWithMaxLength();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

// Проверка поля "CVC";

    // Негативный тест: Поле 'CVC' заполнено невалидными данными;
    @Test
    @Order(30)
    void shouldGetErrorIfInvalidCVCField() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getInvalidCVCField();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' состоит из спецсимволов;
    @Test
    @Order(31)
    void shouldGetErrorIfCVCFieldWithSpecialCharacters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCVCFieldWithSpecialCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' пустое;
    @Test
    @Order(32)
    void shouldGetErrorIfCVCFieldEmpty() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCVCFieldEmpty();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' состоит из одного символа;
    @Test
    @Order(33)
    void shouldGetErrorIfCVCFieldConsistsOneCharacters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCVCFieldConsistsOneCharacters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }

    // Негативный тест: Поле 'CVC' состоит из букв;
    @Test
    @Order(34)
    void shouldGetErrorIfCVCFieldConsistsOfLetters() {
        debitCard = titlePage.debitCardPayment();
        var invalidDebitCard = DataGenerator.CardInfo.getCVCFieldConsistsOfLetters();
        debitCard.invalidFillFieldDebitCard(invalidDebitCard);
        debitCard.errorMessageIncorrectFormat();
    }
}