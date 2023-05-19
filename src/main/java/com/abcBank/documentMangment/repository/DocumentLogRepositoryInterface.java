package com.abcBank.documentMangment.repository;

import com.abcBank.documentMangment.model.DocumentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentLogRepositoryInterface extends JpaRepository<DocumentLog, Long> {
}
