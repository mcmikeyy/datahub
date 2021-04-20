package com.linkedin.metadata.builders.graph;

import com.linkedin.common.urn.ProviderUrn;
import com.linkedin.data.template.RecordTemplate;
import com.linkedin.metadata.builders.graph.relationship.BaseRelationshipBuilder;
import com.linkedin.metadata.dao.utils.ModelUtils;
import com.linkedin.metadata.entity.ProviderEntity;
import com.linkedin.metadata.snapshot.ProviderSnapshot;
import com.linkedin.provider.ProviderInfo;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ProviderGraphBuilder extends BaseGraphBuilder<ProviderSnapshot> {

    private static final Set<BaseRelationshipBuilder> RELATIONSHIP_BUILDERS =
            Collections.unmodifiableSet(new HashSet<BaseRelationshipBuilder>());

    public ProviderGraphBuilder() {
        super(ProviderSnapshot.class, RELATIONSHIP_BUILDERS);
    }

    @Nonnull
    @Override
    protected List<? extends RecordTemplate> buildEntities(@Nonnull ProviderSnapshot snapshot) {
        final ProviderUrn urn = snapshot.getUrn();
        final ProviderEntity entity = new ProviderEntity().setUrn(urn)
                .setRemoved(!isProviderActive(snapshot))
                .setName(urn.getName());

        return Collections.singletonList(entity);
    }

    static boolean isProviderActive(@Nonnull ProviderSnapshot snapshot) {
        final Optional<ProviderInfo> providerInfo = ModelUtils.getAspectFromSnapshot(snapshot, ProviderInfo.class);
        if (providerInfo.isPresent()) {
            return providerInfo.get().isActive();
        }
        return true;
    }
}
