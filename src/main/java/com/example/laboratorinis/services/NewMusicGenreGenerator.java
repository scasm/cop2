package com.example.laboratorinis.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.Random;

@Alternative
@ApplicationScoped
public class NewMusicGenreGenerator  implements GenreGenerator, Serializable {

    private final static String[] genres = {
            "Pop Punk",
            "Indie Rock",
            "Electronic Dance Music (EDM)",
            "Neo-Soul",
            "Emo",
            "Post-rock",
            "Metalcore",
            "Dubstep",
            "K-pop",
            "Trap",
            "Folktronica",
            "Chillwave",
            "Garage Rock Revival",
            "Synthpop",
            "Alternative R&B"
    };


    @Override
    public String generateGenre() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println("NewMusicGenreGenerator");
        int generateRandomNumber = new Random().nextInt(genres.length);
        System.out.println(generateRandomNumber);
        return genres[generateRandomNumber];
    }
}
