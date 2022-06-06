package kz.bookcrossing.controller;

import io.swagger.annotations.ApiOperation;
import kz.bookcrossing.entity.Chat;
import kz.bookcrossing.entity.dto.ChatDTO;
import kz.bookcrossing.service.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/chat", produces = APPLICATION_JSON_VALUE)
public class ChatContoller {
    private final IChatService chatService;
    public static final String PRIVATE_URL = "/private/ad-chat";
    public static final String PUBLIC_URL = "/public/ad-chat";

    @GetMapping(PUBLIC_URL+"/all")
    @ApiOperation("The list of all chats for advertisements")
    public ResponseEntity<List<Chat>> getAll() {
        return ResponseEntity.ok(chatService.getAll());
    }

    @GetMapping(PUBLIC_URL+"/{id}")
    @ApiOperation(value = "The chat with exact ID")
    public ResponseEntity<Chat> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(chatService.getById(id));
    }

    @PostMapping(PRIVATE_URL)
    @ApiOperation(value = "Create or update chat")
    public ResponseEntity<Chat> createOrUpdateAdChat(@RequestBody ChatDTO chatDTO) {
        return ResponseEntity.ok(chatService.createOrUpdateChat(chatDTO));
    }

    @DeleteMapping(PRIVATE_URL+"/delete/{id}")
    @ApiOperation(value = "Delete chat by ID")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            chatService.deleteChat(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(PRIVATE_URL+"/delete/all")
    @ApiOperation(value = "Delete all chats simultaneously")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            chatService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
