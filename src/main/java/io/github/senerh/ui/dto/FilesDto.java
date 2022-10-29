package io.github.senerh.ui.dto;

import java.util.List;

public record FilesDto(List<FileDto> files, String nextPageToken) {

}
