package com.linkedin.metadata.resources.provider;

import com.linkedin.common.urn.ProviderUrn;
import com.linkedin.metadata.aspect.ProviderAspect;
import com.linkedin.metadata.dao.BaseLocalDAO;
import com.linkedin.metadata.dao.BaseSearchDAO;
import com.linkedin.metadata.restli.BaseSearchableEntityResource;
import com.linkedin.metadata.search.ProviderDocument;
import com.linkedin.metadata.snapshot.ProviderSnapshot;
import com.linkedin.provider.Provider;
import com.linkedin.provider.ProviderKey;
import com.linkedin.restli.common.ComplexResourceKey;
import com.linkedin.restli.common.EmptyRecord;
import com.linkedin.restli.server.annotations.RestLiCollection;

import javax.annotation.Nonnull;

@RestLiCollection(name = "providers", namespace = "com.linkedin.provider", keyName = "provider")
public final class Providers extends BaseSearchableEntityResource<
        // @formatter:off
        ComplexResourceKey<ProviderKey, EmptyRecord>,
        Provider,
        ProviderUrn,
        ProviderSnapshot,
        ProviderAspect,
        ProviderDocument
        > {


    public Providers() {
        super(ProviderSnapshot.class, ProviderAspect.class);
    }
    @Nonnull
    @Override
    protected BaseSearchDAO<ProviderDocument> getSearchDAO() {
        return null;
    }

    @Nonnull
    @Override
    protected BaseLocalDAO<ProviderAspect, ProviderUrn> getLocalDAO() {
        return null;
    }

    @Nonnull
    @Override
    protected ProviderUrn createUrnFromString(@Nonnull String urnString) throws Exception {
        return null;
    }

    @Nonnull
    @Override
    protected ProviderUrn toUrn(@Nonnull ComplexResourceKey<ProviderKey, EmptyRecord> providerKeyEmptyRecordComplexResourceKey) {
        return null;
    }

    @Nonnull
    @Override
    protected ComplexResourceKey<ProviderKey, EmptyRecord> toKey(@Nonnull ProviderUrn urn) {
        return null;
    }

    @Nonnull
    @Override
    protected Provider toValue(@Nonnull ProviderSnapshot providerSnapshot) {
        return null;
    }

    @Nonnull
    @Override
    protected ProviderSnapshot toSnapshot(@Nonnull Provider provider, @Nonnull ProviderUrn urn) {
        return null;
    }
    // @formatter:on
}
