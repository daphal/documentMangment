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
@SQLDelete(sql = "UPDATE Document SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Document implements Serializable {

    @Id
    @GeneratedValue()
    @Column(name = "Id")
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
    private UserDetails userDetails;


    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "documents",fetch = FetchType.EAGER)
    private List<DocumentLog> documentLogs;
}

