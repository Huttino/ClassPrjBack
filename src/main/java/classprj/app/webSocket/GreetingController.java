package classprj.app.webSocket;


import classprj.app.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@CrossOrigin(origins = "*")
public class GreetingController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/send/message")
    public void greet(@Payload @Valid SentMessage sent, Principal user){
        System.out.println(sent);
        if(sent.getUsername().equals(user.getName())){
            this.simpMessagingTemplate.setUserDestinationPrefix("/message");
           simpMessagingTemplate.convertAndSend("/message.classes"+sent.getClassId() ,new ReturnMessage(sent.getName(),sent.getSurname(),sent.getMessage()) );
        }
        else throw new ApiException("wrong username");
    }
}
