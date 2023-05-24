package com.abcBank.documentMangment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "UserDetails")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class UserDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private Integer user_Id;

    @NotNull
    @Pattern(regexp = CommonResponseData.ONLY_ALPHABATES)
    @Column(name = "userName")
    private String userName;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetails", fetch = FetchType.EAGER)
    private List<Document> documents;
}
