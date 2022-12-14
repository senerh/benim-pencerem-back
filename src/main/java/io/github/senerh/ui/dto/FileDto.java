package io.github.senerh.ui.dto;

import java.time.LocalDate;
import java.util.List;

public record FileDto(String id,
                      String name,
                      String mimeType,
                      String description,
                      List<String> tags,
                      String smallThumbnailLink,
                      String largeThumbnailLink,
                      String downloadLink,
                      String previewLink,
                      LocalDate creationDate) {

}
