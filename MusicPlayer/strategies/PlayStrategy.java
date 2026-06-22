package MusicPlayer.strategies;

import MusicPlayer.models.Playlist;
import MusicPlayer.models.Song;

public interface PlayStrategy {
    void setPlaylist(Playlist playlist);
    Song next();
    boolean hasNext();
    Song previous();
    boolean hasPrevious();
    default void addToNext(Song song) {}
}