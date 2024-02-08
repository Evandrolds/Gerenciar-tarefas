package com.evandro.gerenciar.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,length = 20,nullable = false)
    private String name;
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.PERSIST)
    private List<User> users= new ArrayList<>();
}
