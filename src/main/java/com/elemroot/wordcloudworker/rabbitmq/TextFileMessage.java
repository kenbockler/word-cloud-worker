package com.elemroot.wordcloudworker.rabbitmq;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextFileMessage implements Serializable {
    private UUID id;
    private String content;
    private int chunkNumber;
    private int totalChunks;
}