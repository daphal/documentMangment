package com.abcBank.documentMangment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "DocumentLog")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class DocumentLog {

    @Id
    @GeneratedValue()
    @Column(name = "Id")
    private Integer documentLog_Id;

    @NotNull
    @Column(name = "documentModifedTime")
    private LocalDateTime documentModifedTime= LocalDateTime.now();;

    @ManyToOne
    @JoinColumn(name="document_Id", nullable=false)
    private Document document;

}
