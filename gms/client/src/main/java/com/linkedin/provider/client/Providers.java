package com.linkedin.provider.client;


import com.linkedin.data.template.StringArray;
import com.linkedin.metadata.query.SortCriterion;
import com.linkedin.metadata.restli.BaseSearchableClient;
import com.linkedin.provider.Provider;
import com.linkedin.provider.ProvidersFindBySearchRequestBuilder;
import com.linkedin.provider.ProvidersRequestBuilders;
import com.linkedin.r2.RemoteInvocationException;
import com.linkedin.restli.client.Client;
import com.linkedin.restli.common.CollectionResponse;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

import static com.linkedin.metadata.dao.utils.QueryUtils.newFilter;

public class Providers extends BaseSearchableClient<Provider> {

    private static final ProvidersRequestBuilders PROVIDERS_REQUEST_BUILDERS = new ProvidersRequestBuilders();

    public Providers(@Nonnull Client restliClient) {
        super(restliClient);
    }

    @Nonnull
    @Override
    public CollectionResponse<Provider> search(@Nonnull String input, @Nullable StringArray aspectNames,
                                               @Nullable Map<String, String> requestFilters,
                                               @Nullable SortCriterion sortCriterion,
                                               int start, int count) throws RemoteInvocationException {
        final ProvidersFindBySearchRequestBuilder requestBuilder = PROVIDERS_REQUEST_BUILDERS.findBySearch()
                .aspectsParam(aspectNames)
                .inputParam(input)
                .sortParam(sortCriterion)
                .paginate(start, count);
        if (requestFilters != null) {
            requestBuilder.filterParam(newFilter(requestFilters));
        }
        return _client.sendRequest(requestBuilder.build()).getResponse().getEntity();
    }
}
