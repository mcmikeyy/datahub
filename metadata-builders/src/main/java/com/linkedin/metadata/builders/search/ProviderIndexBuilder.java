package com.linkedin.metadata.builders.search;

import com.linkedin.data.template.RecordTemplate;
import com.linkedin.metadata.search.ProviderDocument;
import com.linkedin.metadata.search.TagDocument;
import com.linkedin.metadata.snapshot.ProviderSnapshot;
import com.linkedin.metadata.snapshot.TagSnapshot;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ProviderIndexBuilder extends BaseIndexBuilder<ProviderDocument> {

    public ProviderIndexBuilder() {
        super(Collections.singletonList(ProviderSnapshot.class), ProviderDocument.class);
    }


    @Nullable
    @Override
    public List<ProviderDocument> getDocumentsToUpdate(@Nonnull RecordTemplate snapshot) {
        return null;
    }

    @Nonnull
    @Override
    public Class<ProviderDocument> getDocumentType() {
        return ProviderDocument.class;
    }
}
