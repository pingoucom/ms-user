package com.pingou.msuser.domain.broker.producer;

import com.pingou.msuser.domain.entity.User;

public interface UserProducer {
    public void produceUserCreationMessage(User user);

    public void produceUserDeletionMessage(User user);
}
