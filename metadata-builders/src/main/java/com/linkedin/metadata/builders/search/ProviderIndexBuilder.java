package com.linkedin.metadata.builders.search;

import com.linkedin.common.urn.ProviderUrn;
import com.linkedin.data.template.RecordTemplate;
import com.linkedin.metadata.search.ProviderDocument;
import com.linkedin.metadata.snapshot.CorpGroupSnapshot;
import com.linkedin.metadata.snapshot.ProviderSnapshot;
import com.linkedin.provider.ProviderInfo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class ProviderIndexBuilder extends BaseIndexBuilder<ProviderDocument> {

    public ProviderIndexBuilder() {
        super(Collections.singletonList(ProviderSnapshot.class), ProviderDocument.class);
    }


    @Nullable
    @Override
    public List<ProviderDocument> getDocumentsToUpdate(@Nonnull RecordTemplate snapshot) {

        if (snapshot instanceof CorpGroupSnapshot) {
            return getDocumentsToUpdateFromSnapshotType((ProviderSnapshot) snapshot);
        }
        return Collections.emptyList();
    }

    @Nonnull
    @Override
    public Class<ProviderDocument> getDocumentType() {
        return ProviderDocument.class;
    }

    @Nonnull
    private ProviderDocument getDocumentToUpdateFromAspect(@Nonnull ProviderUrn urn,
                                                           @Nonnull ProviderInfo providerInfo) {
        return new ProviderDocument().setUrn(urn)
                .setName(urn.getName())
                .setDescription(providerInfo.getDescription());
    }

    @Nonnull
    private List<ProviderDocument> getDocumentsToUpdateFromSnapshotType(@Nonnull ProviderSnapshot providerSnapshot) {
        final ProviderUrn urn = providerSnapshot.getUrn();
        return providerSnapshot.getAspects().stream().map(aspect -> {
            if (aspect.isProviderInfo()) {
                return getDocumentToUpdateFromAspect(urn, aspect.getProviderInfo());
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
