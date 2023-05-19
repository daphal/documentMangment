package com.abcBank.documentMangment.repository;

import com.abcBank.documentMangment.model.Document;
import com.abcBank.documentMangment.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepositoryInterface  extends JpaRepository<Document, Integer> {
    List<Document> findByUserDetails(UserDetails userDetails);
}
