package kz.bookcrossing.controller;

import kz.bookcrossing.entity.Conversation;
import kz.bookcrossing.service.IConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConversationController {
    public static final String PRIVATE_URL = "/private/conversation";
    private final IConversationService conversationService;

    @GetMapping(PRIVATE_URL + "/author/{author}")
    public ResponseEntity<List<Conversation>> getAllConversationsByAuthor(@PathVariable("author") String author) {
        return ResponseEntity.ok(conversationService.getAllConversationsByAuthor(author));
    }

    @GetMapping(PRIVATE_URL + "/between/{author}/{responder}")
    public ResponseEntity<List<Conversation>> getAllConversationsByAuthorOrResponder(@PathVariable("author") String author,
                                                                @PathVariable("responder") String responder) {
        return ResponseEntity.ok(conversationService.getAllConversationsByAuthorOrResponder(author, responder));
    }
}
