package com.genspark.SpringBootProject22.Service;

import com.genspark.SpringBootProject22.Entity.Card;
import com.genspark.SpringBootProject22.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class CardServiceImpl implements CardService{
    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> getAllCards() {
        return this.cardRepository.findAll();
    }

    @Override
    public String getRandomCard() {
        // create our mysql database connection
        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/springboot2";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "password");
            //grabs a random row to be displayed
            String query = "SELECT *FROM tbl_cards ORDER BY RAND() Limit 1";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String num = null;
            String suit = null;
            while (rs.next()) {
                num = rs.getString("num");
                suit = rs.getString("suit");
            }
            return "you drew the " + num +" of "+ suit;


            } catch (Exception e) {
            e.printStackTrace();
        }

        return "No Card was drawn";
    }

    @Override
    public Card addCard(Card card) {
        return this.cardRepository.save(card);
    }

    @Override
    public Card updateCard(Card card) {
        return this.cardRepository.save(card);
    }

    @Override
    public String deleteCard(int index) {
        this.cardRepository.deleteById(index);
        return "Card was removed from the deck.";
    }
}
