package com.linkedin.metadata;


import com.linkedin.common.urn.ProviderUrn;
import com.linkedin.metadata.configs.ProviderSearchConfig;
import com.linkedin.metadata.search.ProviderDocument;
import com.linkedin.metadata.testing.BaseSearchSanityTests;
import com.linkedin.metadata.testing.SearchIndex;
import com.linkedin.metadata.testing.annotations.SearchIndexMappings;
import com.linkedin.metadata.testing.annotations.SearchIndexSettings;
import com.linkedin.metadata.testing.annotations.SearchIndexType;

import javax.annotation.Nonnull;

public class ProviderSearchSanityTest extends BaseSearchSanityTests<ProviderDocument> {
    @SearchIndexType(ProviderDocument.class)
    @SearchIndexSettings("/index/provider/settings.json")
    @SearchIndexMappings("/index/provider/mappings.json")
    public SearchIndex<ProviderDocument> _index;

    private static final ProviderUrn URN = new ProviderUrn("provider1");
    private static final ProviderDocument DOCUMENT = new ProviderDocument()
            .setUrn(URN);

    protected ProviderSearchSanityTest() {
        super(URN, DOCUMENT, new ProviderSearchConfig());
    }

    @Nonnull
    @Override
    public SearchIndex<ProviderDocument> getIndex() {
        return _index;
    }
}
