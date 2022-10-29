package io.github.senerh.infra.mapper;

import com.google.api.services.drive.model.FileList;
import io.github.senerh.domain.model.File;
import io.github.senerh.domain.model.Files;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class FileListMapper {

    @Inject
    FileMapper fileMapper;

    public Files toDomain(FileList fileList) {
        List<File> files = fileMapper.toDomain(fileList.getFiles());
        return new Files(files, fileList.getNextPageToken());
    }
}
