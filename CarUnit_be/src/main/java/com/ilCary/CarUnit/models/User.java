package com.ilCary.CarUnit.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String lastname;

    private boolean ceo;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy="user")
    @JsonManagedReference("task")
    private List<Task> tasks;

    @OneToMany(mappedBy="user")
    @JsonManagedReference
    private List<StarredSearch> starredSearch;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "dealership_id")
    private Dealership dealership;

    //@JsonBackReference(value = "popi")
//    @OneToOne
//    @JoinColumn(name = "dealership_ceo_id")
//    private Dealership dealershipCeo;

    @ManyToOne(cascade= {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Address address;

    @Column(nullable = false)
    private String password;
    private Boolean active = true;

    @ManyToMany
    @JoinTable( name = "users_roles" ,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role r) {
        this.roles.add(r);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", tasks=" + tasks +
                ", starredSearch=" + starredSearch +
                ", dealership=" + dealership +
                ", address=" + address +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}