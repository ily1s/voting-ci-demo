package org.example.vote.factory;

import org.example.vote.repo.InMemoryVoteRepository;
import org.example.vote.repo.VoteRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryFactoryTest {

    @Test
    void shouldCreateInMemoryRepository() {
        VoteRepository repo =
                RepositoryFactory.createRepo("memory");

        assertNotNull(repo);
        assertTrue(repo instanceof InMemoryVoteRepository);
    }
}
