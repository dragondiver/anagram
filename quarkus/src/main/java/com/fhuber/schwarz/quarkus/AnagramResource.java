package com.fhuber.schwarz.quarkus;

import java.net.URL;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fhuber.schwarz.alternate.service.Anagram2Service;

import org.jboss.resteasy.reactive.RestSseElementType;

import io.smallrye.mutiny.Multi;

@Path("/anagram")
public class AnagramResource {
    @Inject
    Anagram2Service service;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType(MediaType.TEXT_PLAIN)
    @Path("/{filename}")
    public Multi<Object> anagramsAsStream(String filename) {
        URL url = AnagramResource.class.getClassLoader().getResource("sample.txt");
        String path = url.getPath();
        return Multi.createFrom().items(
                service.process(path).map(value -> value.getAnagramsAsString()));
    }

}
