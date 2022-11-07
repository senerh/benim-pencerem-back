package io.github.senerh.ui.resource;

import io.github.senerh.ui.dto.FileDto;
import io.github.senerh.ui.dto.FilesDto;
import io.github.senerh.ui.service.FilesDtoService;

import javax.inject.Inject;
import javax.ws.rs.*;
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

    @Path("/{fileId}")
    @GET
    public FileDto getFileDto(@PathParam("fileId") String fileId) {
        return filesDtoService.findFileDto(fileId);
    }
}
