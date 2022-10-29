package io.github.senerh.ui.resource;

import io.github.senerh.ui.dto.FilesDto;
import io.github.senerh.ui.service.FilesDtoService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/files")
@Produces(MediaType.APPLICATION_JSON)
public class FileResource {

    @Inject
    FilesDtoService filesDtoService;

    @GET
    public FilesDto getFilesDto(@QueryParam("q") String query, @QueryParam("pageToken") String pageToken) {
        return filesDtoService.findFileDtos(query, pageToken);
    }
}
