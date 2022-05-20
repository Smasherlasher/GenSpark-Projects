package com.genspark.SpringBootProject22.Entity;

import javax.persistence.*;

@Entity
@Table(name="tbl_Cards")
public class Card {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String num;
    @Column
    private String suit;

    public Card(int index, String num, String suit) {
        this.id = index;
        this.num = num;
        this.suit = suit;
    }

    public Card(){}

    public int getIndex() {return id;}

    public void setIndex(int index) {
        this.id = index;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "index=" + id +
                ", num='" + num + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }
}
