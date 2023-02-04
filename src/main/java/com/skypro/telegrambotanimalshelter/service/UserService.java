package com.skypro.telegrambotanimalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.telegrambotanimalshelter.model.User;
import com.skypro.telegrambotanimalshelter.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Service for working with users
 * <br>
 * Properties: <b>telegramBot</b>, <b>userRepository</b>
 * @see UserRepository
 * @see TelegramBot
 * @version 0.0.1
 * @author Bulat Bazarov
 */
@Service
public class UserService {

    private final TelegramBot telegramBot;
    private final UserRepository userRepository;

    /**
     * Constructor - creates a bean UserService in Spring framework with dependencies
     * @param telegramBot
     * @param userRepository
     */
    public UserService(TelegramBot telegramBot, UserRepository userRepository) {
        this.telegramBot = telegramBot;
        this.userRepository = userRepository;
    }

    /**
     * Method for adding user. Takes parameters:
     * @param chatId
     * @param age
     * @param address
     * @param phone
     * @param name
     */
    public void addUser(Long chatId, int age, String address, String phone, String name) {
        User user = new User(name, phone, chatId);
        user.setUserChatId(chatId);
        user.setUserAge(age);
        user.setUserAddress(address);
        user.setUserPhone(phone);
        user.setUserAbleToAdoptPet(false);
        userRepository.save(user);
        telegramBot.execute(new SendMessage(chatId, "Ваши данные успешно сохранены"));
    }
}
