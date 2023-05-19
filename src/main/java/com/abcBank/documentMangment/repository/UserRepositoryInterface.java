package com.abcBank.documentMangment.repository;

import com.abcBank.documentMangment.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryInterface extends JpaRepository<UserDetails, Integer> {
}
