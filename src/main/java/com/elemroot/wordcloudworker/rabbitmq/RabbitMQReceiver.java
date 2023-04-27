package com.elemroot.wordcloudworker.rabbitmq;

import com.elemroot.wordcloudworker.models.TextFile;
import com.elemroot.wordcloudworker.services.TextFileService;
import com.elemroot.wordcloudworker.services.WordProcessingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RabbitMQReceiver {

    private final TextFileService textFileService;
    private final WordProcessingService wordProcessingService;
    private Map<UUID, ConcurrentHashMap<Integer, Map<String, Integer>>> processingResults = new ConcurrentHashMap<>();

    public RabbitMQReceiver(TextFileService textFileService, WordProcessingService wordProcessingService) {
        this.textFileService = textFileService;
        this.wordProcessingService = wordProcessingService;
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(TextFileMessage message) {
        UUID id = message.getId();
        int chunkNumber = message.getChunkNumber();
        int totalChunks = message.getTotalChunks();

        // Update the TextFile status to PROCESSING
        textFileService.updateTextFileStatus(id, TextFile.Status.PROCESSING);

        // Process the chunk
        Map<String, Integer> chunkResult = wordProcessingService.processText(message.getContent());

        // Save the chunk result
        processingResults.computeIfAbsent(id, k -> new ConcurrentHashMap<>()).put(chunkNumber, chunkResult);

        // Check if all chunks are processed
        if (processingResults.get(id).size() == totalChunks) {
            // Combine the results from all chunks
            Map<String, Integer> combinedResult = new HashMap<>();
            for (Map<String, Integer> result : processingResults.get(id).values()) {
                result.forEach((word, count) -> combinedResult.merge(word, count, Integer::sum));
            }

            // Save the combined result to the database
            wordProcessingService.saveWordCounts(id, combinedResult);

            // Update the TextFile status to COMPLETED
            textFileService.updateTextFileStatus(id, TextFile.Status.COMPLETED);

            // Remove the processing results from the map
            processingResults.remove(id);
        }
    }
}
