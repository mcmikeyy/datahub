package com.linkedin.provider.client;


import com.linkedin.common.urn.TagUrn;
import com.linkedin.data.template.StringArray;
import com.linkedin.metadata.query.SortCriterion;
import com.linkedin.metadata.restli.BaseSearchableClient;
import com.linkedin.provider.Provider;
import com.linkedin.r2.RemoteInvocationException;
import com.linkedin.restli.client.Client;
import com.linkedin.restli.client.GetRequest;
import com.linkedin.restli.common.CollectionResponse;
import com.linkedin.restli.common.ComplexResourceKey;
import com.linkedin.restli.common.EmptyRecord;
import com.linkedin.tag.Tag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class Providers extends BaseSearchableClient<Provider> {
    public Providers(@Nonnull Client restliClient) {
        super(restliClient);
    }

    @Nonnull
    @Override
    public CollectionResponse<Provider> search(@Nonnull String input, @Nullable StringArray aspectNames, @Nullable Map<String, String> requestFilters, @Nullable SortCriterion sortCriterion, int start, int count) throws RemoteInvocationException {
        return null;
    }
}
