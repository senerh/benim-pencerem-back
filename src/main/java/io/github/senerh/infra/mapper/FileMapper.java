package io.github.senerh.infra.mapper;

import com.google.api.client.util.DateTime;
import io.github.senerh.domain.model.File;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class FileMapper {

    public File toDomain(com.google.api.services.drive.model.File file) {
        if (file == null) {
            return null;
        }
        String largeThumbnailLink = file.getThumbnailLink() == null ? null : file.getThumbnailLink().replaceAll("=s\\d+", "");
        return new File(
                file.getId(),
                file.getName(),
                file.getMimeType(),
                buildDescription(file.getDescription()),
                findTags(file.getDescription()),
                file.getThumbnailLink(),
                largeThumbnailLink,
                file.getWebContentLink(),
                file.getWebViewLink(),
                mapDateTimeToDomain(file.getCreatedTime())
        );
    }

    public List<File> toDomain(List<com.google.api.services.drive.model.File> files) {
        if (files == null) {
            return List.of();
        }
        return files.stream().filter(Objects::nonNull).map(this::toDomain).toList();
    }

    private List<String> findTags(String description) {
        if (description == null) {
            return List.of();
        }
        Pattern pattern = Pattern.compile("(#[^\\s.#]+)");
        Matcher matcher = pattern.matcher(description);
        List<String> tags = new ArrayList<>();
        while (matcher.find()) {
            tags.add(matcher.group(1));
        }
        return tags;
    }

    private LocalDate mapDateTimeToDomain(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return LocalDate.ofInstant(Instant.ofEpochMilli(dateTime.getValue()), TimeZone.getDefault().toZoneId());
    }

    private String buildDescription(String description) {
        if (description == null) {
            return null;
        }
        return description.replaceAll("#{1}([^\\s.#]+)\\s?", "");
    }
}
