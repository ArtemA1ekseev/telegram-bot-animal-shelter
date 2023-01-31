package com.skypro.telegrambotanimalshelter.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.telegrambotanimalshelter.listener.TelegramBotUpdatesListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A <b>custom</b> class that implements <b>buttons</b> in the telegram bot interface.
 * @author Artem Alekseev
 * @version 0.1.1
 * @data 01.02.2023
 */
@Service
public class KeyBoardShelter {

    @Autowired
    private TelegramBot telegramBot;

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    /**
     * A method for displaying a menu with a basic set of buttons.
     * <br>
     * Class methods used: {@code ReplyKeyboardMarkup}:
     * <br>
     * {@link ReplyKeyboardMarkup#addRow(String...)}
     * <br>
     * {@link ReplyKeyboardMarkup#resizeKeyboard(boolean)}
     * <br>
     * {@link ReplyKeyboardMarkup#selective(boolean)}
     * <br>
     * Class methods used: {@code SendMessage}:
     * <br>
     * {@link SendMessage#replyMarkup(Keyboard)}
     * <br>
     * {@link SendMessage#parseMode(ParseMode)}
     * <br>
     * {@link SendMessage#disableWebPagePreview(boolean)}
     * @param chatId
     * @see KeyBoardShelter
     */
    public void sendMenu(long chatId) {
        logger.info("Method sendMessage has been run: {}, {}", chatId, "Вызвано основное меню");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Информация о возможностях бота"),
                new KeyboardButton("Узнать информацию о приюте"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Как взять питомца из приюта"),
                new KeyboardButton("Прислать отчет о питомце"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"));

        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(false);

        SendMessage request = new SendMessage(chatId, "Добро пожаловать, в наш приют")
                .replyMarkup(replyKeyboardMarkup)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);

        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("code of error: {}", codeError);
            logger.info("description -: {}", description);
        }
    }

    /**
     * The way to display the menu with information about the shelter.
     * <br>
     * Class methods used: {@code ReplyKeyboardMarkup}:
     * <br>
     * {@link ReplyKeyboardMarkup#addRow(String...)}
     * <br>
     * {@link ReplyKeyboardMarkup#resizeKeyboard(boolean)}
     * <br>
     * {@link ReplyKeyboardMarkup#selective(boolean)}
     * <br>
     * Class methods used: {@code SendMessage}:
     * <br>
     * {@link SendMessage#replyMarkup(Keyboard)}
     * <br>
     * {@link SendMessage#parseMode(ParseMode)}
     * <br>
     * {@link SendMessage#disableWebPagePreview(boolean)}
     * @param chatId
     * @see KeyBoardShelter
     */
    public void sendMenuInfoShelter(long chatId) {
        logger.info("Method sendMenuInfoShelter has been run: {}, {}", chatId, "Вызвали Информация о приюте");

        ReplyKeyboardMarkup replyKeyboardMarkup2 = new ReplyKeyboardMarkup(new KeyboardButton("Кидает на статью"),
                new KeyboardButton("Оставить контактные данные"));
        replyKeyboardMarkup2.addRow(new KeyboardButton("Позвать волонтера"),
                new KeyboardButton("Вернуться в меню"));

        replyKeyboardMarkup2.resizeKeyboard(true);
        replyKeyboardMarkup2.oneTimeKeyboard(false);
        replyKeyboardMarkup2.selective(false);

        SendMessage request = new SendMessage(chatId, "Информацию о приюте")
                .replyMarkup(replyKeyboardMarkup2)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);

        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("code of error: {}", codeError);
            logger.info("description -: {}", description);
        }
    }

    /**
     * A way to display a menu with information about how to take a pet.
     * <br>
     * Class methods used: {@code ReplyKeyboardMarkup}:
     * <br>
     * {@link ReplyKeyboardMarkup#addRow(String...)}
     * <br>
     * {@link ReplyKeyboardMarkup#resizeKeyboard(boolean)}
     * <br>
     * {@link ReplyKeyboardMarkup#selective(boolean)}
     * <br>
     * Class methods used: {@code SendMessage}:
     * <br>
     * {@link SendMessage#replyMarkup(Keyboard)}
     * <br>
     * {@link SendMessage#parseMode(ParseMode)}
     * <br>
     * {@link SendMessage#disableWebPagePreview(boolean)}
     * @param chatId
     * @see KeyBoardShelter
     */
    public void sendMenuTakeAnimal(long chatId) {
        logger.info("Method sendMenuTakeAnimal has been run: {}, {}", chatId, "вызвали Как взять питомца из приюта");

        ReplyKeyboardMarkup replyKeyboardMarkup3 = new ReplyKeyboardMarkup(new KeyboardButton("Кидает на статью"),
                new KeyboardButton("Оставить контактные данные"));
        replyKeyboardMarkup3.addRow(new KeyboardButton("Позвать волонтера"),
                new KeyboardButton("Вернуться в меню"));

        replyKeyboardMarkup3.resizeKeyboard(true);
        replyKeyboardMarkup3.oneTimeKeyboard(false);
        replyKeyboardMarkup3.selective(false);

        SendMessage request = new SendMessage(chatId, "Информацию о приюте")
                .replyMarkup(replyKeyboardMarkup3)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);

        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("code of error: {}", codeError);
            logger.info("description -: {}", description);
        }
    }
}
