package de.schloebe.seriesguide.streamingfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.battlelancer.seriesguide.api.Action;
import com.battlelancer.seriesguide.api.Episode;
import com.battlelancer.seriesguide.api.Movie;
import com.battlelancer.seriesguide.api.SeriesGuideExtension;

public class StreamingFinderExtensionService extends SeriesGuideExtension {
    public static final String TAG = "StreamingFinderExtension";

    public StreamingFinderExtensionService() {
        super("StreamingFinderExtension");
    }

    @Override
    protected void onRequest(int episodeIdentifier, Episode episode) {
        publishGoogleAction(episodeIdentifier, episode.getShowTitle());
    }

    @Override
    protected void onRequest(int movieIdentifier, Movie movie) {
        publishGoogleAction(movieIdentifier, movie.getTitle());
    }

    private void publishGoogleAction(int identifier, String searchTerm) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String serviceKey = SP.getString("streamingService", "justwatchus");

        String baseUrl = (String)getResources().getText(
                getResources().getIdentifier(
                        "searchurl_" + serviceKey,
                        "string",
                        "de.schloebe.seriesguide.streamingfinder"
                )
        );

        publishAction(new Action.Builder(getString(R.string.app_name), identifier)
                .viewIntent(new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(baseUrl + searchTerm)))
                .build());
    }
}
