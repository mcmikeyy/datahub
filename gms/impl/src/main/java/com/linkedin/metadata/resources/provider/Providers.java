package com.linkedin.metadata.resources.provider;

import com.linkedin.common.Ownership;
import com.linkedin.common.urn.ProviderUrn;
import com.linkedin.common.urn.Urn;
import com.linkedin.metadata.aspect.ProviderAspect;
import com.linkedin.metadata.dao.BaseLocalDAO;
import com.linkedin.metadata.dao.BaseSearchDAO;
import com.linkedin.metadata.dao.utils.ModelUtils;
import com.linkedin.metadata.query.AutoCompleteResult;
import com.linkedin.metadata.query.Filter;
import com.linkedin.metadata.query.SearchResultMetadata;
import com.linkedin.metadata.query.SortCriterion;
import com.linkedin.metadata.restli.BackfillResult;
import com.linkedin.metadata.restli.BaseSearchableEntityResource;
import com.linkedin.metadata.search.ProviderDocument;
import com.linkedin.metadata.snapshot.ProviderSnapshot;
import com.linkedin.parseq.Task;
import com.linkedin.provider.Provider;
import com.linkedin.provider.ProviderKey;
import com.linkedin.restli.common.ComplexResourceKey;
import com.linkedin.restli.common.EmptyRecord;
import com.linkedin.restli.server.CollectionResult;
import com.linkedin.restli.server.PagingContext;
import com.linkedin.restli.server.annotations.Action;
import com.linkedin.restli.server.annotations.ActionParam;
import com.linkedin.restli.server.annotations.Finder;
import com.linkedin.restli.server.annotations.Optional;
import com.linkedin.restli.server.annotations.PagingContextParam;
import com.linkedin.restli.server.annotations.QueryParam;
import com.linkedin.restli.server.annotations.RestLiCollection;
import com.linkedin.restli.server.annotations.RestMethod;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.linkedin.metadata.restli.RestliConstants.*;

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


    @Inject
    @Named("providerDao")
    private BaseLocalDAO<ProviderAspect, ProviderUrn> _localDAO;

    @Inject
    @Named("providerSearchDao")
    private BaseSearchDAO _esSearchDAO;

    public Providers() {
        super(ProviderSnapshot.class, ProviderAspect.class);
    }

    @Override
    @Nonnull
    protected BaseLocalDAO getLocalDAO() {
        return _localDAO;
    }

    @Nonnull
    @Override
    protected BaseSearchDAO<ProviderDocument> getSearchDAO() {
        return _esSearchDAO;
    }

    @Nonnull
    @Override
    protected ProviderUrn createUrnFromString(@Nonnull String urnString) throws Exception {
        return ProviderUrn.createFromUrn(Urn.createFromString(urnString));
    }


    @Override
    @Nonnull
    protected ProviderUrn toUrn(@Nonnull ComplexResourceKey<ProviderKey, EmptyRecord> key) {
        return new ProviderUrn(key.getKey().getName());
    }

    @Override
    @Nonnull
    protected ComplexResourceKey<ProviderKey, EmptyRecord> toKey(@Nonnull ProviderUrn urn) {
        return new ComplexResourceKey<>(new ProviderKey().setName(urn.getName()), new EmptyRecord());
    }

    @Override
    @Nonnull
    protected Provider toValue(@Nonnull ProviderSnapshot snapshot) {
        final Provider value = new Provider();
        value.setName(snapshot.getUrn().getName());
        ModelUtils.getAspectsFromSnapshot(snapshot).forEach(aspect -> {
           if (aspect instanceof Ownership) {
                value.setOwnership((Ownership) aspect);
            }
        }); //ProviderInfo, Ownership, Status, GlobalProviders
        return value;
    }

    @Override
    @Nonnull
    protected ProviderSnapshot toSnapshot(@Nonnull Provider provider, @Nonnull ProviderUrn providerUrn) {
        final List<ProviderAspect> aspects = new ArrayList<>();
        if (provider.hasOwnership()) {
            aspects.add(ModelUtils.newAspectUnion(ProviderAspect.class, provider.getOwnership()));
        }
        return ModelUtils.newSnapshot(ProviderSnapshot.class, providerUrn, aspects);
    }

    @RestMethod.Get
    @Override
    @Nonnull
    public Task<Provider> get(@Nonnull ComplexResourceKey<ProviderKey, EmptyRecord> key,
                         @QueryParam(PARAM_ASPECTS) @Optional @Nullable String[] aspectNames) {
        return super.get(key, aspectNames);
    }

    @RestMethod.BatchGet
    @Override
    @Nonnull
    public Task<Map<ComplexResourceKey<ProviderKey, EmptyRecord>, Provider>> batchGet(
            @Nonnull Set<ComplexResourceKey<ProviderKey, EmptyRecord>> keys,
            @QueryParam(PARAM_ASPECTS) @Optional @Nullable String[] aspectNames) {
        return super.batchGet(keys, aspectNames);
    }

    @RestMethod.GetAll
    @Nonnull
    public Task<List<Provider>> getAll(@PagingContextParam @Nonnull PagingContext pagingContext) {
        return super.getAll(pagingContext);
    }

    @Finder(FINDER_SEARCH)
    @Override
    @Nonnull
    public Task<CollectionResult<Provider, SearchResultMetadata>> search(@QueryParam(PARAM_INPUT) @Nonnull String input,
                                                                    @QueryParam(PARAM_ASPECTS) @Optional @Nullable String[] aspectNames,
                                                                    @QueryParam(PARAM_FILTER) @Optional @Nullable Filter filter,
                                                                    @QueryParam(PARAM_SORT) @Optional @Nullable SortCriterion sortCriterion,
                                                                    @PagingContextParam @Nonnull PagingContext pagingContext) {
        return super.search(input, aspectNames, filter, sortCriterion, pagingContext);
    }

    @Action(name = ACTION_AUTOCOMPLETE)
    @Override
    @Nonnull
    public Task<AutoCompleteResult> autocomplete(@ActionParam(PARAM_QUERY) @Nonnull String query,
                                                 @ActionParam(PARAM_FIELD) @Nullable String field, @ActionParam(PARAM_FILTER) @Nullable Filter filter,
                                                 @ActionParam(PARAM_LIMIT) int limit) {
        return super.autocomplete(query, field, filter, limit);
    }

    @Action(name = ACTION_INGEST)
    @Override
    @Nonnull
    public Task<Void> ingest(@ActionParam(PARAM_SNAPSHOT) @Nonnull ProviderSnapshot snapshot) {
        return super.ingest(snapshot);
    }

    @Action(name = ACTION_GET_SNAPSHOT)
    @Override
    @Nonnull
    public Task<ProviderSnapshot> getSnapshot(@ActionParam(PARAM_URN) @Nonnull String urnString,
                                         @ActionParam(PARAM_ASPECTS) @Optional @Nullable String[] aspectNames) {
        return super.getSnapshot(urnString, aspectNames);
    }

    @Action(name = ACTION_BACKFILL)
    @Override
    @Nonnull
    public Task<BackfillResult> backfill(@ActionParam(PARAM_URN) @Nonnull String urnString,
                                         @ActionParam(PARAM_ASPECTS) @Optional @Nullable String[] aspectNames) {
        return super.backfill(urnString, aspectNames);
    }


}
