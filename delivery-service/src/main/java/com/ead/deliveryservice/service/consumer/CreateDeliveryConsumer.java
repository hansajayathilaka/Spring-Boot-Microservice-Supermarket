package com.ead.deliveryservice.service.consumer;


import com.ead.deliveryservice.model.dto.CreateDeliveryDto;
import com.ead.deliveryservice.service.DeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CreateDeliveryConsumer {

    private final DeliveryService deliveryService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "create.delivery", groupId = "delivery-id")
    public void handleCreateDelivery(String createDeliveryEventMessage) throws JsonProcessingException {
        log.info("create delivery event consumed {}", createDeliveryEventMessage);

        CreateDeliveryDto createDeliveryDto = objectMapper.readValue(createDeliveryEventMessage, CreateDeliveryDto.class);
        deliveryService.create(createDeliveryDto);
    }
}
