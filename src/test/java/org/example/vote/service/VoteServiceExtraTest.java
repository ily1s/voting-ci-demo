package org.example.vote.service;

import org.example.vote.model.Vote;
import org.example.vote.repo.InMemoryVoteRepository;
import org.example.vote.strategy.PluralityCountingStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoteServiceExtraTest {

    @Test
    void countOnEmptyRepositoryReturnsEmptyResult() {
        VoteService service =
                new VoteService(new InMemoryVoteRepository());

        var result =
                service.count(new PluralityCountingStrategy());

        assertTrue(result.isEmpty());
    }
}
