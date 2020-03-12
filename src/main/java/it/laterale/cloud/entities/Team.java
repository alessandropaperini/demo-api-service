package it.laterale.cloud.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * The type Team.
 */
@Entity
@Data
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "members")
    private int members;

    @Column(name = "primary_color")
    private String primaryColor;
}
