package kz.bookcrossing.config;

import kz.bookcrossing.entity.User;
import kz.bookcrossing.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Configuration
@AllArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final UserRepository userRepository;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/secured/history", "/secured/reply");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/secured/ws")
                .setAllowedOrigins("http://localhost:4201", "http://localhost:4200", "http://localhost:3000", "http://localhost")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Nullable
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        try {
                            final String email = request.getURI().getQuery();
                            User employee = userRepository.findByLogin(email);
                            if (employee != null) {
                                employee.setOnline(true);
                                userRepository.save(employee);
                            }

                            return () -> email;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }

                }).withSockJS();
    }
}
