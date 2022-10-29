package io.github.senerh.domain.model;

import io.github.senerh.domain.util.Validations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Files {

    private final List<File> files;
    private final String nextPageToken;

    public Files(List<File> files, String nextPageToken) {
        this.files = Validations.notNull(files, "Files.files").stream().map(f -> Validations.notNull(f, "Files.file")).collect(Collectors.toList());
        this.nextPageToken = nextPageToken;
    }

    public List<File> getFiles() {
        return new ArrayList<>(files);
    }

    public String getNextPageToken() {
        return nextPageToken;
    }
}
