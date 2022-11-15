package io.github.senerh.domain.model;

import io.github.senerh.domain.util.Validations;

import java.time.LocalDate;
import java.util.List;

public class File {

    private final String id;
    private final String name;
    private final String mimeType;
    private final String description;
    private final List<String> tags;
    private final String smallThumbnailLink;
    private final String largeThumbnailLink;
    private final String downloadLink;
    private final String previewLink;
    private final LocalDate creationDate;

    public File(String id, String name, String mimeType, String description, List<String> tags, String smallThumbnailLink, String largeThumbnailLink, String downloadLink, String previewLink, LocalDate creationDate) {
        this.id = Validations.notBlank(id, "File.id");
        this.name = Validations.notBlank(name, "File.name").trim();
        this.mimeType = Validations.notBlank(mimeType, "File.mimeType").trim();
        this.description = description;
        this.tags = Validations.notNull(tags, "File.tags").stream().map(t -> Validations.notBlank(t, "File.tag")).distinct().toList();
        this.smallThumbnailLink = Validations.notBlank(smallThumbnailLink, "File.smallThumbnailLink").trim();
        this.largeThumbnailLink = Validations.notBlank(largeThumbnailLink, "File.largeThumbnailLink").trim();
        this.downloadLink = Validations.notBlank(downloadLink, "File.downloadLink").trim();
        this.previewLink = Validations.notBlank(previewLink, "File.previewLink").trim();
        this.creationDate = Validations.notNull(creationDate, "File.creationDate");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getSmallThumbnailLink() {
        return smallThumbnailLink;
    }

    public String getLargeThumbnailLink() {
        return largeThumbnailLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
