package com.jpabook.start.ch7_pg274;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*@Getter
@Setter
@Entity*/
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    @OneToOne
    @JoinTable(name = "PARENT_CHILD",
            joinColumns = @JoinColumn(name = "PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID"))
    private Child child;

}
