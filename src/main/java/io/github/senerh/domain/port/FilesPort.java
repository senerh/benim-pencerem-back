package io.github.senerh.domain.port;

import io.github.senerh.domain.model.File;
import io.github.senerh.domain.model.Files;
import io.github.senerh.domain.model.FindFilesQuery;

public interface FilesPort {

    Files findFiles(FindFilesQuery query);

    File findFile(String fileId);
}
