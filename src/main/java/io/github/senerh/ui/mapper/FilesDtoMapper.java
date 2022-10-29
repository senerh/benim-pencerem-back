package io.github.senerh.ui.mapper;

import io.github.senerh.domain.model.Files;
import io.github.senerh.ui.dto.FileDto;
import io.github.senerh.ui.dto.FilesDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class FilesDtoMapper {

    @Inject
    FileDtoMapper fileDtoMapper;

    public FilesDto toDto(Files files) {
        List<FileDto> fileDtos = fileDtoMapper.toDtos(files.getFiles());
        return new FilesDto(fileDtos, files.getNextPageToken());
    }
}
