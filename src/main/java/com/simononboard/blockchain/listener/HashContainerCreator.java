package com.simononboard.blockchain.listener;

import com.simononboard.blockchain.dao.model.HashContainer;
import com.simononboard.blockchain.dao.repository.HashContainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class HashContainerCreator implements ApplicationListener<ApplicationReadyEvent> {
    private final HashContainerRepository hashContainerRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("checking hashcontainer info");
        List<HashContainer> hashContainerList;
        if (!(hashContainerList = hashContainerRepository.findAll()).isEmpty()) {
            log.info("Size of container (should be 1): {}", hashContainerList.size());
            log.info("CurrentHash and position info: {}", hashContainerList.get(0).toString());
        } else {
            HashContainer hashContainer = HashContainer.builder()
                    .id(0L)
                    .currentNum(0)
                    .currentHash(null)
                    .build();
            hashContainer = hashContainerRepository.save(hashContainer);
            log.info("Created hash container: {}", hashContainer.toString());
        }
    }
}