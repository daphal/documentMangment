package com.abcBank.documentMangment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Document")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@Where(clause = "deleted = false")
public class Document implements Serializable {

    @Id
    @GeneratedValue()
    @Column(name = "document_Id")
    private Integer document_Id;

    @NotNull
    @Column(name = "documentName")
    private String documentName;

    @NotNull
    @Column(name = "documentData")
    @Lob
    private String documentData;

    @Column(name = "documentType")
    private String documentType;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name="user_Id", nullable=false)
    private UserDetails userDetails;


    @Column(name = "deleted")
    private Boolean deleted = false;

    @JsonManagedReference
    @OneToMany(mappedBy = "documents",fetch = FetchType.EAGER)
    private List<DocumentLog> documentLogs;
}

