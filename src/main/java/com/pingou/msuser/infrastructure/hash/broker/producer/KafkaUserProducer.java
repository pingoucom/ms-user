package com.pingou.msuser.infrastructure.hash.broker.producer;

import com.pingou.msuser.application.dto.UserDTO;
import com.pingou.msuser.domain.broker.producer.UserProducer;
import com.pingou.msuser.domain.entity.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaUserProducer implements UserProducer {
    private final String USER_CREATED_TOPIC = "user-created";

    private final String USER_DELETED_TOPIC = "user-deleted";

    private final KafkaTemplate<String, UserDTO> userCreationKafkaTemplate;

    private final KafkaTemplate<String, UserDTO> userDeletionKafkaTemplate;

    KafkaUserProducer(KafkaTemplate<String, UserDTO> userCreationKafkaTemplate, KafkaTemplate<String, UserDTO> userDeletionKafkaTemplate) {
        this.userCreationKafkaTemplate = userCreationKafkaTemplate;
        this.userDeletionKafkaTemplate = userDeletionKafkaTemplate;
    }

    public void produceUserCreationMessage(User user) {
        UserDTO userDTO = new UserDTO(user);

        userCreationKafkaTemplate.send(USER_CREATED_TOPIC, user.getId(), userDTO);
    }

    public void produceUserDeletionMessage(User user) {
        UserDTO userDTO = new UserDTO(user);

        userDeletionKafkaTemplate.send(USER_DELETED_TOPIC, user.getId(), userDTO);
    }
}
