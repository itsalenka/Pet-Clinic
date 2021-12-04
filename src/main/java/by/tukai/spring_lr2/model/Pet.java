package by.tukai.spring_lr2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "pets")
@Data
public class Pet extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "breed")
    private String breed;

    @Column(name = "gender")
    private String gender;

    @Column(name = "bday")
    private String bday;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private List<Appointment> appointments;
}