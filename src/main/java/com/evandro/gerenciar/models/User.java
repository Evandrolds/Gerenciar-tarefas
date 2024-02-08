package com.evandro.gerenciar.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_users")
public class User implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,length = 50,nullable = false)
    private String username;
    @Column(length = 50,nullable = false)
    private String password;
    
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name =
            "user_id"),inverseJoinColumns = @JoinColumn(name =
            "role_id"),uniqueConstraints = @UniqueConstraint(columnNames
            = {"user_id","role_id"}))
    private List<Role> roles = new ArrayList<>();
}
