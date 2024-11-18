package com.taher.book.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taher.book.user;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role
{   @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToMany(mappedBy="roles")
    @JsonIgnore
    private List<user> users ;















}