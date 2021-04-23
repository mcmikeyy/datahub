package com.linkedin.metadata.resources.provider;

import com.linkedin.parseq.Task;
import com.linkedin.provider.ProviderInfo;
import com.linkedin.restli.server.CreateResponse;
import com.linkedin.restli.server.annotations.Optional;
import com.linkedin.restli.server.annotations.QueryParam;
import com.linkedin.restli.server.annotations.RestLiCollection;
import com.linkedin.restli.server.annotations.RestMethod;

import javax.annotation.Nonnull;

/**
 * Rest.li entry point: /providers/{providerKey}/providerInfo
 */
@RestLiCollection(name = "providerInfo", namespace = "com.linkedin.provider", parent = Providers.class)
public final class ProviderInfoResource extends BaseProvidersAspectResource<ProviderInfo> {

    public ProviderInfoResource() {
        super(ProviderInfo.class);
    }

    @Nonnull
    @Override
    @RestMethod.Create
    public Task<CreateResponse> create(@Nonnull ProviderInfo providerInfo) {
        return super.create(providerInfo);
    }

    @Nonnull
    @Override
    @RestMethod.Get
    public Task<ProviderInfo> get(@QueryParam("version") @Optional("0") @Nonnull Long version) {
        return super.get(version);
    }
}
