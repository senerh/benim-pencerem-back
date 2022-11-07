package io.github.senerh.infra.adapter;

import com.google.api.services.drive.model.FileList;
import io.github.senerh.domain.model.File;
import io.github.senerh.domain.model.Files;
import io.github.senerh.domain.model.FindFilesQuery;
import io.github.senerh.domain.port.FilesPort;
import io.github.senerh.infra.exception.GoogleDriveException;
import io.github.senerh.infra.mapper.FileListMapper;
import io.github.senerh.infra.mapper.FileMapper;
import io.github.senerh.infra.util.DriveHelper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.stream.Collectors;

@ApplicationScoped
public class FilesAdapter implements FilesPort {

    @Inject
    FileListMapper fileListMapper;

    @Inject
    FileMapper fileMapper;

    @Override
    public Files findFiles(FindFilesQuery query) {
        try {
            FileList fileList = DriveHelper.getInstance()
                    .files()
                    .list()
                    .setQ(buildQ(query))
                    .setFields("nextPageToken, files(id, name, mimeType, description, thumbnailLink, webContentLink, webViewLink, createdTime)")
                    .setPageSize(10)
                    .setOrderBy(buildOrderBy(query))
                    .setPageToken(query.getPageToken())
                    .execute();
            return fileListMapper.toDomain(fileList);
        } catch (IOException e) {
            throw new GoogleDriveException("Querying Google Drive API throws an exception", e);
        }
    }

    @Override
    public File findFile(String fileId) {
        try {
            var file = DriveHelper.getInstance()
                    .files()
                    .get(fileId)
                    .setFields("id, name, mimeType, description, thumbnailLink, webContentLink, webViewLink, createdTime")
                    .execute();
            return fileMapper.toDomain(file);
        } catch (IOException e) {
            throw new GoogleDriveException("Querying Google Drive API throws an exception", e);
        }
    }

    private String buildQ(FindFilesQuery query) {
        StringBuilder sb = new StringBuilder("mimeType != 'application/vnd.google-apps.folder'");
        if (query.isNotEmpty()) {
            sb.append(" and (");
            String filters = query.getFields().stream()
                    .map(field -> field.replace("'", "\\'"))
                    .map(field -> "fullText contains '" + field + "'")
                    .collect(Collectors.joining(" or "));
            sb.append(filters);
            sb.append(")");
        }
        return sb.toString();
    }

    private String buildOrderBy(FindFilesQuery query) {
        return query.isEmpty() ? "createdTime desc" : null;
    }
}
