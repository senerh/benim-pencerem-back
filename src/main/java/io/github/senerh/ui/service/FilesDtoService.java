package io.github.senerh.ui.service;

import io.github.senerh.domain.model.File;
import io.github.senerh.domain.model.Files;
import io.github.senerh.domain.model.FindFilesQuery;
import io.github.senerh.domain.port.FilesPort;
import io.github.senerh.ui.dto.FileDto;
import io.github.senerh.ui.dto.FilesDto;
import io.github.senerh.ui.mapper.FileDtoMapper;
import io.github.senerh.ui.mapper.FilesDtoMapper;
import io.github.senerh.ui.mapper.QueryMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FilesDtoService {

    @Inject
    FilesPort filesPort;

    @Inject
    QueryMapper queryMapper;

    @Inject
    FilesDtoMapper filesDtoMapper;

    @Inject
    FileDtoMapper fileDtoMapper;

    public FilesDto findFileDtos(String query, String pageToken) {
        FindFilesQuery findFilesQuery = queryMapper.toDomain(query, pageToken);
        Files files = filesPort.findFiles(findFilesQuery);
        return filesDtoMapper.toDto(files);
    }

    public FileDto findFileDto(String fileId) {
        File file = filesPort.findFile(fileId);
        return fileDtoMapper.toDto(file);
    }
}
