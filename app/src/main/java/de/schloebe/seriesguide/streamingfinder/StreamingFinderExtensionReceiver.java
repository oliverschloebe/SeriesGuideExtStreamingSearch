package de.schloebe.seriesguide.streamingfinder;

import com.battlelancer.seriesguide.api.SeriesGuideExtension;
import com.battlelancer.seriesguide.api.SeriesGuideExtensionReceiver;

public class StreamingFinderExtensionReceiver extends SeriesGuideExtensionReceiver {
    @Override
    protected int getJobId() {
        return 1;
    }

    @Override
    protected Class<? extends SeriesGuideExtension> getExtensionClass() {
        return StreamingFinderExtensionService.class;
    }
}