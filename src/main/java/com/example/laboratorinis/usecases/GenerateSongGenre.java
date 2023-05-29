package com.example.laboratorinis.usecases;

import com.example.laboratorinis.interceptors.LoggedInvocation;
import com.example.laboratorinis.services.GenreGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateSongGenre implements Serializable {
    @Inject
    GenreGenerator genreGenerator;

    private CompletableFuture<String> genreGenerationTask = null;

    @LoggedInvocation
    public String generateNewGenre() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        genreGenerationTask = CompletableFuture.supplyAsync(() -> genreGenerator.generateGenre());

        return "/songs.xhtml?faces-redirect=true&authorId=" + requestParameters.get("authorId");
    }


    public boolean isGenreGenerationRunning() {
        return genreGenerationTask != null && !genreGenerationTask.isDone();
    }

    public String getGenreGenerationStatus() throws ExecutionException, InterruptedException {
        if (genreGenerationTask == null) {
            return null;
        } else if (isGenreGenerationRunning()) {
            return "Genre generation in progress";

        }
        return "Suggested genere: " + genreGenerationTask.get();
    }
}
