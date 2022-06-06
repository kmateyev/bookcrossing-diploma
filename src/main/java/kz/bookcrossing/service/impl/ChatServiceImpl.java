package kz.bookcrossing.service.impl;

import kz.bookcrossing.entity.Chat;
import kz.bookcrossing.entity.dto.ChatDTO;
import kz.bookcrossing.repository.ChatRepository;
import kz.bookcrossing.service.IChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChatServiceImpl implements IChatService {
    private final ChatRepository chatRepository;

    @Override
    public List<Chat> getAll() {
        return chatRepository.getAllByDeletedAtIsNull();
    }
    @Override
    public Chat createOrUpdateChat(ChatDTO chat) {
        Chat adChat;
        if (chat.getId() == null) {
            adChat = Chat
                    .builder()
                    .message(chat.getMessage())
                    .userId(chat.getUserId())
                    .created(chat.getCreated())
                    .updated(chat.getUpdated())
                    .shortName(chat.getShortName())
                    .build();
        } else {
            adChat = getById(chat.getId());
            adChat.setCreated(chat.getCreated());
            adChat.setUpdated(chat.getUpdated());
            adChat.setMessage(chat.getMessage());
            adChat.setShortName(chat.getShortName());
            adChat.setUserId(chat.getUserId());
        }
        return chatRepository.saveAndFlush(adChat);
    }

    @Override
    public void deleteChat(Long id) {
        Chat chat = chatRepository.getOne(id);
        chat.setDeletedAt(new Timestamp(new Date().getTime()));
        chatRepository.saveAndFlush(chat);
    }

    @Override
    public void deleteAll() {
        chatRepository.deleteAll();
    }

    @Override
    public Chat getById(Long id) {
        return chatRepository.findById(id).get();
    }
}
