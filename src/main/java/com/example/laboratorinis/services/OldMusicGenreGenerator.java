package com.example.laboratorinis.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.Random;

@Alternative
@ApplicationScoped
public class OldMusicGenreGenerator implements GenreGenerator, Serializable {
    private final static String[] genres = {
            "Classical",
            "Jazz",
            "Blues",
            "Rock and Roll",
            "Country",
            "R&B (Rhythm and Blues)",
            "Reggae",
            "Funk",
            "Disco",
            "Punk Rock",
            "New Wave",
            "Heavy Metal",
            "Hip Hop",
            "Alternative Rock",
            "Grunge"
    };


    @Override
    public String generateGenre() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println("OldMusicGenreGenerator");
        int generateRandomNumber = new Random().nextInt(genres.length);
        System.out.println(generateRandomNumber);
        return genres[generateRandomNumber - 1];
    }
}
