package com.ead.deliveryservice.service;

import com.ead.deliveryservice.model.dto.CreateDeliveryDto;
import com.ead.deliveryservice.model.dto.DeliveryResponse;
import com.ead.deliveryservice.model.dto.DeliveryResponseDto;
import com.ead.deliveryservice.model.dto.UpdateDeliveryDto;
import com.ead.deliveryservice.model.entry.Delivery;
import com.ead.deliveryservice.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Override
    public void create(CreateDeliveryDto createDeliveryDto) {
        deliveryRepository.save(mapToEntity(createDeliveryDto));
    }


    @Override
    public DeliveryResponseDto getDelivery() {
        List<Delivery> delivery = deliveryRepository.findAll();
        if (!delivery.isEmpty()) {
            List<DeliveryResponse> deliveryResponses = delivery.stream()
                    .map(_delivery -> {
                        return DeliveryResponse.builder()
                                .id(_delivery.getId())
                                .refOrder(_delivery.getRefOrder())
                                .status(_delivery.getStatus())
                                .address(_delivery.getAddress())
                                .notes(_delivery.getNotes())
                                .build();
                    }).collect(java.util.stream.Collectors.toList());
            return DeliveryResponseDto.builder().deliveryResponses(deliveryResponses).build();
        }

        DeliveryResponseDto deliveryResponseDto = new DeliveryResponseDto();
        deliveryResponseDto.setDeliveryResponses(null);
        deliveryResponseDto.setError(true);
        deliveryResponseDto.setDescription("Delivery does not contain in database");
        return deliveryResponseDto;
    }

    @Override
    public DeliveryResponseDto getDeliveryByOrderId(String orderId) {
        Optional<Delivery> delivery = deliveryRepository.findByRefOrder(orderId);
        DeliveryResponse deliveryResponse = delivery.map(value -> DeliveryResponse.builder()
                .id(value.getId())
                .refOrder(value.getRefOrder())
                .status(value.getStatus())
                .address(value.getAddress())
                .notes(value.getNotes())
                .build()).orElse(null);
        if (deliveryResponse == null) {
            DeliveryResponseDto deliveryResponseDto = new DeliveryResponseDto();
            deliveryResponseDto.setDeliveryResponses(null);
            deliveryResponseDto.setError(true);
            deliveryResponseDto.setDescription("Delivery does not contain in database");
            return deliveryResponseDto;
        }
        List<DeliveryResponse> deliveryResponses = List.of(deliveryResponse);
        return DeliveryResponseDto.builder().deliveryResponses(deliveryResponses).build();
    }

    @Override
    public void updateDeliveryStatus(UpdateDeliveryDto updateDeliveryDto) {
        Optional<Delivery> delivery = deliveryRepository.findById(updateDeliveryDto.getDeliveryId());
        if (delivery.isPresent()) {
            Delivery _delivery = delivery.get();
            _delivery.setStatus(updateDeliveryDto.getStatus());
            deliveryRepository.save(_delivery);
        } else {
            log.error("Delivery not found with deliveryId: {}", updateDeliveryDto.getDeliveryId());
        }
    }


    private Delivery mapToEntity(CreateDeliveryDto createDeliveryDto){
        return Delivery.builder()
                .refOrder(createDeliveryDto.getRefOrder())
                .status(Delivery.DeliveryStatus.PENDING)
                .address(createDeliveryDto.getAddress())
                .notes(createDeliveryDto.getNotes())
                .isActive(true)
                .build();
    }
}
