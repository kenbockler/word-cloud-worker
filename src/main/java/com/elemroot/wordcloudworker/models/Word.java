package com.elemroot.wordcloudworker.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "textfile_id", nullable = false)
    private TextFile textFile;

    @Column(name = "word", nullable = false)
    private String word;

    @Column(name = "count", nullable = false)
    private Integer count;

}