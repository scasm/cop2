package com.example.laboratorinis.usecases;

import com.example.laboratorinis.entities.Author;
import com.example.laboratorinis.persistence.AuthorsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Authors {
    @Inject
    private AuthorsDAO authorsDAO;

    @Getter
    @Setter
    private Author authorToCreate = new Author();

    @Getter
    private List<Author> allAuthors;

    @Transactional
    public void createTeam(){
        this.authorsDAO.persist(authorToCreate);
    }

    private void loadAllTeams(){
        this.allAuthors = authorsDAO.loadAll();
    }


}
