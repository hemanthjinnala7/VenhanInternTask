package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="Borrower")
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class BorrowerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MembershipId;

    @Column
    private String Name;

    @Column
    private Long Contact;

    @Column(name = "BookIds")
    private List<Long> borrowedBookIds;

    public Long getMembershipId() {
        return MembershipId;
    }

    public void setMembershipId(Long membershipId) {
        MembershipId = membershipId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getContact() {
        return Contact;
    }

    public void setContact(Long contact) {
        Contact = contact;
    }

    public List<Long> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public void setBorrowedBookIds(List<Long> borrowedBookIds) {
        this.borrowedBookIds = borrowedBookIds;
    }
}
