package android.support.v4.media.session;

import android.os.*;
import android.media.session.*;
import java.util.*;

class PlaybackStateCompatApi22
{
    public static Bundle getExtras(final Object o) {
        return ((PlaybackState)o).getExtras();
    }
    
    public static Object newInstance(final int n, final long n2, final long bufferedPosition, final float n3, final long actions, final CharSequence errorMessage, final long n4, final List<Object> list, final long activeQueueItemId, final Bundle extras) {
        final PlaybackState$Builder playbackState$Builder = new PlaybackState$Builder();
        playbackState$Builder.setState(n, n2, n3, n4);
        playbackState$Builder.setBufferedPosition(bufferedPosition);
        playbackState$Builder.setActions(actions);
        playbackState$Builder.setErrorMessage(errorMessage);
        final Iterator<PlaybackState$CustomAction> iterator = list.iterator();
        while (iterator.hasNext()) {
            playbackState$Builder.addCustomAction((PlaybackState$CustomAction)iterator.next());
        }
        playbackState$Builder.setActiveQueueItemId(activeQueueItemId);
        playbackState$Builder.setExtras(extras);
        return playbackState$Builder.build();
    }
}
