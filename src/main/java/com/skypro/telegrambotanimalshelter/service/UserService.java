package com.skypro.telegrambotanimalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.telegrambotanimalshelter.model.User;
import com.skypro.telegrambotanimalshelter.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final TelegramBot bot;
    private final UserRepository repository;

    public UserService(TelegramBot bot, UserRepository repository) {
        this.bot = bot;
        this.repository = repository;
    }

    public void addUser(Long chatId, int age, String address, String phone, String name) {
        User user = new User(name, phone, chatId);
        user.setUserChatId(chatId);
        user.setUserAge(age);
        user.setUserAddress(address);
        user.setUserPhone(phone);
        user.setUserAbleToAdoptPet(false);
        repository.save(user);
        bot.execute(new SendMessage(chatId, "Ваши данные успешно сохранены"));
    }
}
