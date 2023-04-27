package com.elemroot.wordcloudworker.services;

import com.elemroot.wordcloudworker.models.TextFile;
import com.elemroot.wordcloudworker.models.Word;
import com.elemroot.wordcloudworker.repositories.WordRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class WordProcessingService {

    private static final Pattern WORD_PATTERN = Pattern.compile("\\b\\w+\\b");

    private final TextFileService textFileService;

    private final WordRepository wordRepository;

    public WordProcessingService(TextFileService textFileService, WordRepository wordRepository) {
        this.textFileService = textFileService;
        this.wordRepository = wordRepository;
    }
    public Map<String, Integer> processText(String content) {
        Map<String, Integer> wordCounts = new HashMap<>();
        WORD_PATTERN.matcher(content)
                .results()
                .map(matchResult -> matchResult.group().toLowerCase())
                .forEach(word -> wordCounts.merge(word, 1, Integer::sum));
        return wordCounts;
    }

    public void saveWordCounts(UUID fileId, Map<String, Integer> wordCounts) {
        TextFile textFile = textFileService.getTextFileById(fileId)
                .orElseThrow(() -> new IllegalStateException("No TextFile found with id: " + fileId));

        List<Word> words = new ArrayList<>();
        wordCounts.forEach((wordText, count) -> {
            Word word = new Word(null, textFile, wordText, count);
            words.add(word);
        });

        wordRepository.saveAll(words);
    }
}
