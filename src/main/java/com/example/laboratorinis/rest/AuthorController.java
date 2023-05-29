package com.example.laboratorinis.rest;

import com.example.laboratorinis.entities.Author;
import com.example.laboratorinis.entities.Song;
import com.example.laboratorinis.persistence.AuthorsDAO;
import com.example.laboratorinis.rest.dto.AuthorCreationDTO;
import com.example.laboratorinis.rest.dto.AuthorRetrievalDTO;
import com.example.laboratorinis.rest.dto.SongDTO;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/authors")
public class AuthorController {
    @Inject
    @Setter @Getter
    private AuthorsDAO authorsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById (@PathParam("id") final Long id) {
        Author author = authorsDAO.findOne(id);
        if(author == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        AuthorRetrievalDTO authorRetrievalDTO = new AuthorRetrievalDTO();
        authorRetrievalDTO.setName(author.getName());
        authorRetrievalDTO.setSurname(author.getSurname());
        authorRetrievalDTO.setNationality(author.getNationality());

        List<SongDTO> songDTOList = new ArrayList<>();
        for(Song song : author.getSongs()) {
            SongDTO songDTO = new SongDTO();
            songDTO.setId(song.getId());
            songDTO.setTitle(song.getTitle());
            songDTO.setGenre(song.getGenre());
            songDTOList.add(songDTO);
        }
        authorRetrievalDTO.setSongs(songDTOList);
        return Response.ok(authorRetrievalDTO).build();

    }
    //NOTE TO UPDATE THE AUTHOR MODEL SO IT WOULD GET VERSIONS
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Long authorId,
            AuthorCreationDTO authorCreationDTO) {
        try {
            Author existingAuthor = authorsDAO.findOne(authorId);
            if(existingAuthor == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingAuthor.setName(authorCreationDTO.getName());
            existingAuthor.setSurname(authorCreationDTO.getSurname());
            existingAuthor.setNationality(authorCreationDTO.getNationality());
            authorsDAO.update(existingAuthor);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(AuthorCreationDTO authorCreationDTO) {
        Author author = new Author();
        author.setName(authorCreationDTO.getName());
        author.setSurname(authorCreationDTO.getSurname());
        author.setNationality(authorCreationDTO.getNationality());
        //NOTE TO DO THE SAME
        authorsDAO.persist(author);
        return Response.created(URI.create("http://localhost:8080/laboratorinis/api/authors/"+author.getId())).entity("http://localhost:8080/laboratorinis/api/authors/"+author.getId()).build();

    }

}
