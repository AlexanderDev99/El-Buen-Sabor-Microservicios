package com.elbuensabor.usuario.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.time.LocalDateTime;

@Component
public class AuditInterceptor implements HandlerInterceptor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.application.name}")
    private String serviceName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Obtener URI y Método (Ej: GET /api/usuario/1)
        String endpoint = request.getRequestURI();
        String method = request.getMethod();
        String timestamp = LocalDateTime.now().toString();

        // Formatear el mensaje según la instrucción 4
        String logMessage = String.format("[%s] Microservicio: %s | Endpoint: %s %s", 
                            timestamp, serviceName.toUpperCase(), method, endpoint);

        // Enviar a la cola de RabbitMQ
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, logMessage);

        return true; // Permite que la petición siga su curso
    }
}
