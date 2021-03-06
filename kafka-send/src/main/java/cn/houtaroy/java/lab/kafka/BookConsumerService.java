package cn.houtaroy.java.lab.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author Houtaroy
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class BookConsumerService {
  @Value("${kafka.topic.my-topic}")
  private String myTopic;
  @Value("${kafka.topic.my-topic2}")
  private String myTopic2;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = {"${kafka.topic.my-topic}"}, groupId = "group1")
  public void consumeMessage(ConsumerRecord<String, String> record, Acknowledgment ack) {
    LOGGER.info("消费者消费topic:{} partition:{}的消息 -> {}", record.topic(), record.partition(), record.value());
    ack.acknowledge();
  }

  @KafkaListener(topics = {"${kafka.topic.my-topic2}"}, groupId = "group2")
  public void consumeMessage2(Book book) {
    LOGGER.info("消费者消费topic:{}的消息 -> {}", myTopic2, book.toString());
  }
}
