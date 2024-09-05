package br.com.alurafood.orders.kafka;//package br.com.alurafood.promotions.kafka;

import br.com.alurafood.orders.dto.PromotionDto;
import br.com.alurafood.orders.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PromotionKafkaListener {

    @Autowired
    private ProductService productService;

    @Autowired
    private final ModelMapper modelMapper;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}")
    @RetryableTopic(
            backoff = @Backoff(value = 3000L),
            attempts = "5",
            include = RuntimeException.class)
    public void listenPromotionsTopic(@Payload PromotionDto promotion) {
        if (promotion.getProductId() != null) {
            productService.updateHasPromotion(true, promotion.getProductId());
            log.info("Message id : {}. name : {} discount: {} product : {}",
                    promotion.getId(), promotion.getName(), promotion.getDiscountPercentage(),
                    promotion.getProductId());
        } else  {
            throw new RuntimeException();
        }
    }
}
