package com.skypro.telegrambotanimalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.telegrambotanimalshelter.keyboard.KeyBoardShelter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.List;

/**
 * A <b>custom</b> class that implements communication between a user and a <b>Telegram-bot</b>. Allows you to catch the main changes in the behavior of the bot.
 * @author Artem Alekseev
 * @version 0.1.3
 * @see UpdatesListener
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private static final String START_CMD = "/start";

    //private static final String GREETING_TEXT = ", Приветствую! Чтобы найти то, что тебе нужно - нажми на нужную кнопку";

    //private static final String INVALID_ID_NOTIFY_OR_CMD = "Такой команды не существует";

    @Autowired
    private KeyBoardShelter keyBoardShelter;

    @Autowired
    private TelegramBot telegramBot;

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Redefined <b>"process"</b> method, receiving a message from the user.
     * @param updates available updates
     * @return {@code UpdatesListener.CONFIRMED_UPDATES_ALL}
     * @see TelegramBotUpdatesListener
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {

            logger.info("Processing update: {}", update);

            String nameUser = update.message().chat().firstName();
            String textUpdate = update.message().text();
            long chatId = update.message().chat().id();

            if (textUpdate.equals(START_CMD)) {
                keyBoardShelter.sendMenu(chatId);
            } else if (textUpdate.equals("Как взять питомца из приюта")) {
                keyBoardShelter.sendMenuTakeAnimal(chatId);
            } else if (textUpdate.equals("Узнать информацию о приюте")) {
                keyBoardShelter.sendMenuInfoShelter(chatId);
            } else if (update.message().text().equals("Вернуться в меню")) {
                keyBoardShelter.sendMenu(chatId);
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Method for sending a message.
     * @param chatId
     * @param messageText
     */
    public void sendMessage(Long chatId, String messageText) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        telegramBot.execute(sendMessage);
    }

    /**
     * Method for sending a message.
     * @param chatId
     * @param text
     */
    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        SendResponse sendResponse = telegramBot.execute(message);
    }
}
