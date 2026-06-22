package MusicPlayer.managers;

import MusicPlayer.strategies.SequentialPlayStrategy;
import MusicPlayer.strategies.RandomPlayStrategy;
import MusicPlayer.strategies.CustomQueueStrategy;
import MusicPlayer.strategies.PlayStrategy;
import MusicPlayer.enums.PlayStrategyType;

public class StrategyManager {
    private static StrategyManager instance = null;
    private SequentialPlayStrategy sequentialStrategy;
    private RandomPlayStrategy randomStrategy;
    private CustomQueueStrategy customQueueStrategy;

    private StrategyManager() {
        sequentialStrategy = new SequentialPlayStrategy();
        randomStrategy = new RandomPlayStrategy();
        customQueueStrategy = new CustomQueueStrategy();
    }

    public static synchronized StrategyManager getInstance() {
        if (instance == null) {
            instance = new StrategyManager();
        }
        return instance;
    }

    public PlayStrategy getStrategy(PlayStrategyType type) {
        if (type == PlayStrategyType.SEQUENTIAL) {
            return sequentialStrategy;
        } else if (type == PlayStrategyType.RANDOM) {
            return randomStrategy;
        } else {
            return customQueueStrategy;
        }
    }
}