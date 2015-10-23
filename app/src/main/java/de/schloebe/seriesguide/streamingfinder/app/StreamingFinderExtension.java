package de.schloebe.seriesguide.streamingfinder.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.battlelancer.seriesguide.api.Action;
import com.battlelancer.seriesguide.api.Episode;
import com.battlelancer.seriesguide.api.SeriesGuideExtension;

public class StreamingFinderExtension extends SeriesGuideExtension {

    public StreamingFinderExtension() {
        super("StreamingFinderExtension");
    }

    @Override
    protected void onRequest(int episodeIdentifier, Episode episode) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String serviceKey = SP.getString("streamingService", "justwatchus");

        String baseUrl = (String)getResources().getText(
                getResources().getIdentifier(
                        "searchurl_" + serviceKey,
                        "string",
                        "de.schloebe.seriesguide.streamingfinder.app"
                )
        );

        publishAction(new Action.Builder(getString(R.string.app_name), episodeIdentifier)
                .viewIntent(new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(baseUrl + episode.getShowTitle())))
                .build());
    }

}
