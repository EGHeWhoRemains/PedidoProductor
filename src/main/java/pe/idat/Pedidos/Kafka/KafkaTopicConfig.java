package pe.idat.Pedidos.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic verificarStockTopic(){
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "15000"); // 15 segundos de retención
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, String.valueOf("10485760")); // 1 GB por segmento
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, String.valueOf("1048576")); // 1 MB por mensaje
        return TopicBuilder.name("verificar-stock")
                .partitions(2)
                .replicas(1)
                .configs(configurations)
                .build();
    }
}
