package com.elemroot.wordcloudworker.services;

import com.elemroot.wordcloudworker.models.TextFile;
import com.elemroot.wordcloudworker.repositories.TextFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TextFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextFileService.class);

    private final TextFileRepository textFileRepository;

    public TextFileService(TextFileRepository textFileRepository) {
        this.textFileRepository = textFileRepository;
    }

    public Optional<TextFile> getTextFileById(UUID id) {
        return textFileRepository.findById(id);
    }

    public void updateTextFileStatus(UUID id, TextFile.Status status) {
        Optional<TextFile> optionalTextFile = textFileRepository.findById(id);
        if (optionalTextFile.isPresent()) {
            TextFile textFile = optionalTextFile.get();
            textFile.setStatus(status);
            textFileRepository.save(textFile);
        } else {
            throw new IllegalStateException("No TextFile found with id: " + id);
        }
    }

}
