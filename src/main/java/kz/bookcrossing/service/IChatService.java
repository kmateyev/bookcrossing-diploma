package kz.bookcrossing.service;

import kz.bookcrossing.entity.Chat;
import kz.bookcrossing.entity.dto.ChatDTO;

import java.util.List;

public interface IChatService {
    List<Chat> getAll();
    Chat getById(Long id);
    Chat createOrUpdateChat(ChatDTO chatDTO);
    void deleteChat(Long id);
    void deleteAll();
}
