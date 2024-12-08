package pe.idat.Pedidos.Kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import pe.idat.Pedidos.Model.DetallePedido;
import pe.idat.Pedidos.Model.Pedido;
import pe.idat.Pedidos.Model.ProductoStockRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class StockProducer {
    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendStockVerificationRequest(ProductoStockRequest productoStockRequest){
        List<ProductoStockRequest> productoParaVerificar = new ArrayList<>();
        System.out.println("Enviando mensaje a kafka: "+ productoStockRequest.toString());
        try {
            productoParaVerificar.add(productoStockRequest);
            String mensaje = new ObjectMapper().writeValueAsString(productoParaVerificar);
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("verificar-stock", mensaje);
            future.thenAccept(result ->{
                if (result != null && result.getProducerRecord() != null){
                    System.out.println("Mensaje enviado al tópico verificar-stock: "+ result.getProducerRecord().value());
                }else {
                    System.out.println("Mensaje enviado, pero no se pudo recuperar el contenido.");
                }
            }).exceptionally(ex ->{
                System.err.println("Error al enviar mensaje al tópico verificar-stock: " + ex.getMessage());
                return null;
            });
        } catch (JsonProcessingException e){
            System.err.println("Error al procesar el mensaje: " + e.getMessage());
        }
    }
    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return properties;
    }
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
