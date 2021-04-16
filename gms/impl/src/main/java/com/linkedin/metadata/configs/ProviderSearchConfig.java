package com.linkedin.metadata.configs;

import com.linkedin.metadata.dao.search.BaseSearchConfig;
import com.linkedin.metadata.dao.utils.SearchUtils;
import com.linkedin.metadata.search.ProviderDocument;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ProviderSearchConfig extends BaseSearchConfig<ProviderDocument> {
    @Nonnull
    @Override
    public Set<String> getFacetFields() {
        return Collections.unmodifiableSet(new HashSet<>(Arrays.asList()));
    }

    @Nonnull
    @Override
    public Class<ProviderDocument> getSearchDocument() {
        return ProviderDocument.class;
    }

    @Nonnull
    @Override
    public String getDefaultAutocompleteField() {
        return "name";
    }

    @Nonnull
    @Override
    public String getSearchQueryTemplate() {
        return SearchUtils.readResourceFile(getClass(), "providerESSearchQueryTemplate.json");
    }

    @Nonnull
    @Override
    public String getAutocompleteQueryTemplate() {
        return SearchUtils.readResourceFile(getClass(), "tagESAutocompleteQueryTemplate.json");
    }
}
