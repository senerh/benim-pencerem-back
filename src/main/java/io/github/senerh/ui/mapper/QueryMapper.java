package io.github.senerh.ui.mapper;

import io.github.senerh.domain.model.FindFilesQuery;
import io.github.senerh.domain.util.Validations;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueryMapper {

    public FindFilesQuery toDomain(String query, String pageToken) {
        if (Validations.isBlank(query)) {
            return FindFilesQuery.of(pageToken);
        }
        return FindFilesQuery.of(query, pageToken);
    }
}
