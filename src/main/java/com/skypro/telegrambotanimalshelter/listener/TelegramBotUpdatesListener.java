package com.skypro.telegrambotanimalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.telegrambotanimalshelter.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skypro.telegrambotanimalshelter.keyboard.KeyBoardShelter;
import com.skypro.telegrambotanimalshelter.repository.UserRepository;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * A <b>custom</b> class that implements communication between a user and a <b>Telegram-bot</b>. Allows you to catch the main changes in the behavior of the bot.
 * @author Artem Alekseev
 * @version 0.1.5
 * @data 31.01.2023
 * @see UpdatesListener
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private static final String START_CMD = "/start";

    private static final String GREETING_TEXT = ", Приветствую! Чтобы найти то, что тебе нужно - нажми на нужную кнопку";

    //private static final String INVALID_ID_NOTIFY_OR_CMD = "Такой команды не существует";

    @Autowired
    private UserRepository userRepository;

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
            Integer messageId = update.message().messageId();
            Long VolunteerChat = 440504531L;

            // @data 02.02.2023 17:08
            if (update.message().contact().phoneNumber() != null) {
                String firstName = update.message().contact().firstName();
                String lastName = update.message().contact().lastName();
                String phone = update.message().contact().phoneNumber();
                long chatId = update.message().chat().id();
                var sortChatId = userRepository.findAll().stream().filter(i -> i.getUserChatId() == chatId)
                        .collect(Collectors.toList());
                if (!sortChatId.isEmpty()) {
                    sendMessage(chatId, "Вы уже в базе");
                    return;
                }
                if (lastName != null) {
                    String name = firstName + " " + lastName;
                    userRepository.save(new User(name, phone, chatId));
                    return;
                }
                userRepository.save(new User(firstName, phone, chatId));
                return;
            }

            //phone = update.message().contact().phoneNumber();
            //if (phone != null) {
            //
            //sendMessage(VolunteerChat, "Тут перезвонить надо " + phone + " " + nameUser);
            //System.out.println(phone);
            //}

            Integer message123 = update.message().forwardFromMessageId();
            long chatId = update.message().chat().id();

            try {
                switch (textUpdate) {
                    case START_CMD:
                        sendMessage(chatId, nameUser + GREETING_TEXT);
                        keyBoardShelter.sendMenu(chatId);
                        break;
                    case "Как взять питомца из приюта":
                        keyBoardShelter.sendMenuTakeAnimal(chatId);
                        break;
                    case "Узнать информацию о приюте":
                        keyBoardShelter.sendMenuInfoShelter(chatId);
                        break;
                    case "Вернуться в меню":
                        keyBoardShelter.sendMenu(chatId);
                        break;
                    case "Привет":
                        if (messageId != null) {
                            sendReplyMessage(chatId, "И тебе привет", messageId);
                            break;
                        }
                    //case "Позвать волонтера":
                        //sendReplyMessage(VolunteerChat, "Тут перезвонить надо " + phone, messageId);
                        //break;
                    case "Contact":
                        if (messageId != null) {
                            sendReplyMessage(chatId, "123", messageId);
                            break;
                        }
                    case "null":
                        System.out.println("Нельзя");
                        sendMessage(chatId, "Я не знаю такой команды(NULL)");
                        break;
                    case "":
                        System.out.println("Нельзя");
                        sendMessage(chatId, "Пустое сообщение");
                        break;

                    default:
                        //if (messageId != null) {
                        //message.replyToMessage().text();
                        //sendMsg(message, "Скоро вам ответят");
                        sendReplyMessage(chatId, "Я не знаю такой команды", messageId);
                        //}
                        break;

                }
            } catch (NullPointerException e) {
                sendReplyMessage(chatId, "Ошибка", messageId);
                System.out.println("Ошибка");
            }

        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Method for sending a message.
     * @param chatId
     * @param messageText
     */
    public void sendReplyMessage(Long chatId, String messageText, Integer messageId) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyToMessageId(messageId);
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

    /**
     * Method for sending a message.
     * @param message
     * @param text
     */
    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage(message, text);
        sendMessage.replyToMessageId(message.messageId());
        SendResponse sendResponse = telegramBot.execute(sendMessage);
    }
}
