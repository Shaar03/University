package com.project.University.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "university",
            groupId = "uni-1"
    )
    void listener(String data){
        System.out.println("Listener received " + data);
    }
}
