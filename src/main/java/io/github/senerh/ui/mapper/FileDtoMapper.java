package io.github.senerh.ui.mapper;

import io.github.senerh.domain.model.File;
import io.github.senerh.ui.dto.FileDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FileDtoMapper {

    public FileDto toDto(File file) {
        return new FileDto(
                file.getId(),
                file.getName(),
                file.getMimeType(),
                file.getDescription(),
                file.getTags(),
                file.getSmallThumbnailLink(),
                file.getLargeThumbnailLink(),
                file.getDownloadLink(),
                file.getPreviewLink(),
                file.getCreationDate()
        );
    }

    public List<FileDto> toDtos(List<File> files) {
        return files.stream().map(this::toDto).toList();
    }
}
