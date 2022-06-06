package kz.bookcrossing.service;



import kz.bookcrossing.entity.Message;
import kz.bookcrossing.entity.PageableCustom;
import kz.bookcrossing.entity.dto.MessageDTO;

import java.util.List;
import java.util.Map;

public interface IMessageService {
    PageableCustom getMessages(Map<String, String> params);
    Message saveMessage(MessageDTO message, Long adDataId);
    List<Message> readMessages(String specialist, String client);
    Long countUnread(String toUserName, String fromUserName);
}
