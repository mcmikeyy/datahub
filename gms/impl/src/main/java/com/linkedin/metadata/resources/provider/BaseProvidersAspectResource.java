package com.linkedin.metadata.resources.provider;

import com.linkedin.common.urn.ProviderUrn;
import com.linkedin.data.template.RecordTemplate;
import com.linkedin.metadata.aspect.ProviderAspect;
import com.linkedin.metadata.dao.BaseLocalDAO;
import com.linkedin.metadata.restli.BaseVersionedAspectResource;
import com.linkedin.provider.ProviderKey;
import com.linkedin.restli.common.ComplexResourceKey;
import com.linkedin.restli.common.EmptyRecord;
import com.linkedin.restli.server.PathKeys;
import com.linkedin.restli.server.annotations.PathKeysParam;
import com.linkedin.restli.server.annotations.RestLiCollection;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;


public class BaseProvidersAspectResource<ASPECT extends RecordTemplate>
    extends BaseVersionedAspectResource<ProviderUrn, ProviderAspect, ASPECT> {

  private static final String PROVIDER_KEY = Providers.class.getAnnotation(RestLiCollection.class).keyName();

  public BaseProvidersAspectResource(Class<ASPECT> aspectClass) {
    super(ProviderAspect.class, aspectClass);
  }

  @Inject
  @Named("providerDao")
  private BaseLocalDAO localDAO;

  @Nonnull
  @Override
  protected BaseLocalDAO<ProviderAspect, ProviderUrn> getLocalDAO() {
    return localDAO;
  }

  @Nonnull
  @Override
  protected ProviderUrn getUrn(@PathKeysParam @Nonnull PathKeys keys) {
    return new ProviderUrn(keys.<ComplexResourceKey<ProviderKey, EmptyRecord>>get(PROVIDER_KEY).getKey().getName());
  }
}