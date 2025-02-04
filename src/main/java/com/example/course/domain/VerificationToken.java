package com.example.course.domain;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;

@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Date exipryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE,expiryTimeInMinutes);
        return new Date(calendar.getTime().getTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExipryDate() {
        return exipryDate;
    }

    public void setExipryDate(Date exipryDate) {
        this.exipryDate = exipryDate;
    }
}
