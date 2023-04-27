package com.elemroot.wordcloudworker.repositories;

import com.elemroot.wordcloudworker.models.TextFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TextFileRepository extends JpaRepository<TextFile, UUID> {
}

