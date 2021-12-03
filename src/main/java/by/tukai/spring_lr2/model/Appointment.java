package by.tukai.spring_lr2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "appointments")
@Data
public class Appointment extends BaseEntity{

    @Column(name = "weight")
    private Float weight;

    @Column(name = "temp")
    private Float temp;

    @Column(name = "age")
    private String age;

    @Column(name = "history")
    private String history;

    @Column(name = "anamnesis")
    private String anamnesis;

    @Column(name = "complaintsC")
    private String complaintsC;

    @Column(name = "conditionC")
    private String conditionC;

    @Column(name = "diagnostics")
    private String diagnostics;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "purpose")
    private String purpose;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
