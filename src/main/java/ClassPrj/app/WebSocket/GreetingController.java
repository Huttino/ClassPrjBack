package ClassPrj.app.WebSocket;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

@Controller
@CrossOrigin(origins = "*")
public class GreetingController {

    @MessageMapping("/send/message")
    @SendTo("/message")
    public GreetingMessage greet(String hello){
        return new GreetingMessage( HtmlUtils.htmlEscape(hello));
    }
}
