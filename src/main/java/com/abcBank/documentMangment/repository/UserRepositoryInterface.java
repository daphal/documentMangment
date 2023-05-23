package com.abcBank.documentMangment.repository;

import com.abcBank.documentMangment.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryInterface extends JpaRepository<UserDetails, Integer> {
   @Query("from UserDetails, Document where document_Id=:documentId")
    //@Query("select user_Id as user_Id ,document_Id as document_Id from Document as a inner join a.userDetails as b ")
    UserDetails findDocumentUserId(Integer documentId);


}
