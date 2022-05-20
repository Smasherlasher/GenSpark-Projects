package com.genspark.SpringBootProject22.Service;

import com.genspark.SpringBootProject22.Entity.Card;

import java.util.List;

public interface CardService {
    List<Card> getAllCards();
    String getRandomCard();
    Card addCard(Card card);
    Card updateCard(Card card);
    String deleteCard(int index);
}
