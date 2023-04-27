package com.elemroot.wordcloudworker.repositories;

import com.elemroot.wordcloudworker.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WordRepository extends JpaRepository<Word, UUID> {
}
