package com.example.laboratorinis.usecases;

import com.example.laboratorinis.interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import com.example.laboratorinis.mybatis.dao.AuthorMapper;
import com.example.laboratorinis.mybatis.model.Author;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class AuthorsMyBatis {
    @Inject
    private AuthorMapper authorMapper;

    @Getter
    private List<Author> allAuthors;

    @Getter @Setter
    private Author authorToCreate = new Author();

    @PostConstruct
    public void init() {
        this.loadAllAuthors();
    }

    private void loadAllAuthors() {
        this.allAuthors = authorMapper.selectAll();
    }

    @Transactional
    @LoggedInvocation
    public String createAuthor() {
        authorMapper.insert(authorToCreate);
        return "/index?faces-redirect=true";
    }
}
