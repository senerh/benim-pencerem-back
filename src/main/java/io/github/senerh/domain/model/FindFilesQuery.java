package io.github.senerh.domain.model;

import io.github.senerh.domain.util.Validations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindFilesQuery {

    private static final int FIELD_MIN_LENGTH = 3;

    private final List<String> fields;
    private final String pageToken;

    public FindFilesQuery(List<String> fields) {
        this(fields, null);
    }

    public FindFilesQuery(List<String> fields, String pageToken) {
        this.fields = Validations.notNull(fields, "FindFilesQuery.fields").stream().map(f -> Validations.notBlank(f, "FindFilesQuery.field")).collect(Collectors.toList());
        this.pageToken = pageToken;
    }

    public static FindFilesQuery of(String pageToken) {
        return new FindFilesQuery(List.of(), pageToken);
    }

    public static FindFilesQuery of(String searchQuery, String pageToken) {
        List<String> fields = Arrays.stream(Validations.notBlank(searchQuery, "searchQuery").trim().split(" "))
                .filter(f -> f.length() >= FIELD_MIN_LENGTH)
                .distinct()
                .collect(Collectors.toList());
        return new FindFilesQuery(fields, pageToken);
    }

    public List<String> getFields() {
        return new ArrayList<>(fields);
    }

    public String getPageToken() {
        return pageToken;
    }

    public boolean isEmpty() {
        return fields.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }
}
