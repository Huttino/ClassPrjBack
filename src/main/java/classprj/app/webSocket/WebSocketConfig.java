package classprj.app.webSocket;

import classprj.app.service.impl.UserDetailsServiceImpl;
import classprj.app.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.filter.OrderedWebFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.security.Principal;
import java.util.Objects;

@Configuration
@EnableWebSocketMessageBroker
@Order(OrderedWebFilter.HIGHEST_PRECEDENCE+99)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER = "Bearer";
    @Autowired
    UserDetailsServiceImpl userDetail;
    @Autowired
    JWTUtils jwtUtils;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket").setAllowedOrigins("*");
        registry.addEndpoint("/socket").setAllowedOrigins("*").withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/message");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        System.out.println("capire");
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor= MessageHeaderAccessor.getAccessor(message,StompHeaderAccessor.class);
                if(StompCommand.CONNECT.equals(accessor.getCommand())){
                    String header=accessor.getFirstNativeHeader(AUTH_HEADER);
                    String jwt=header.substring(BEARER.length()+1);
                    if(StringUtils.hasText(jwt)&& jwtUtils.validateJwtToken(jwt)){
                        String username = jwtUtils.getUsernameFromToken(jwt);

                        UserDetails userDetails = userDetail.loadUserByUsername(username);
                        System.out.println(message);
                        Principal principal= new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        if(Objects.isNull(principal))
                            return null;
                        accessor.setUser(principal);
                    }
                    else if(StompCommand.DISCONNECT.equals(accessor.getCommand())){
                        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
                        if(Objects.nonNull(authentication))
                            System.out.println("Disconnected :"+authentication.getName());
                        else
                            System.out.println("Disconnected :"+accessor.getSessionId());
                    }
                    else {
                        System.out.println("jwt vuoto");
                    }
                }
                return message;
            }
        });
    }
}
