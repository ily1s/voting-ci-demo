package org.example.vote.observer;

import org.example.vote.model.Vote;
import org.junit.jupiter.api.Test;

class LoggingVoteListenerTest {

    @Test
    void shouldNotThrowExceptionOnVote() {
        LoggingVoteListener listener =
                new LoggingVoteListener();

        listener.onVote(
                new Vote("v1", "Alice", System.currentTimeMillis())
        );
    }
}
