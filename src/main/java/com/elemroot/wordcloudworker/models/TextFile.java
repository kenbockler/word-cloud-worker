package com.elemroot.wordcloudworker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "textfiles")
public class TextFile {

    public enum Status {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED
    }

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "file_size", nullable = false)
    private long fileSize;

    @Column(name = "upload_time", nullable = false)
    private LocalDateTime uploadTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
}
