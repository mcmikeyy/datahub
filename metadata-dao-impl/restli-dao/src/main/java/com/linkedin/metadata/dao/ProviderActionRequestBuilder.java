package com.linkedin.metadata.dao;

import com.linkedin.common.urn.ProviderUrn;
import com.linkedin.metadata.snapshot.ProviderSnapshot;

public class ProviderActionRequestBuilder extends BaseActionRequestBuilder<ProviderSnapshot, ProviderUrn> {
    private static final String BASE_URI_TEMPLATE = "providers";

    public ProviderActionRequestBuilder() {
        super(ProviderSnapshot.class, ProviderUrn.class, BASE_URI_TEMPLATE);
    }
}
