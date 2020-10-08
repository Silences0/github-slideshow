package com.example.layuidemo.controller;

import com.example.layuidemo.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/chat")
    public void sentTo(Principal principal, Chat chat) {
        String from = principal.getName();
        chat.setFrom(from);
        simpMessagingTemplate.convertAndSendToUser(chat.getTo(), "/queue/chat", chat);
    }
    //   /app/hello
  /*  @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message hello(Message message){
        return message;
    }
   @MessageMapping("/chat")
    public void sentTo( Chat chat){
        String from = "admin";
        chat.setFrom(from);
        simpMessagingTemplate.convertAndSendToUser(chat.getTo(),"/queue/chat",chat);
    }*/

}
