package org.example.vote;

import org.example.vote.factory.RepositoryFactory;
import org.example.vote.model.Vote;
import org.example.vote.observer.LoggingVoteListener;
import org.example.vote.service.VoteService;
import org.example.vote.strategy.CountingStrategy;
import org.example.vote.strategy.PluralityCountingStrategy;

import java.util.Map;

public class App {

    public static void main(String[] args) {

        // 1️⃣ Choix du repository (Factory)
        var repo = RepositoryFactory.createRepo("memory");

        // 2️⃣ Service
        VoteService service = new VoteService(repo);

        // 3️⃣ Observer (notification)
        service.addListener(new LoggingVoteListener());

        // 4️⃣ Enregistrement de votes (simulation CLI)
        service.cast(new Vote("v1", "Alice", System.currentTimeMillis()));
        service.cast(new Vote("v2", "Alice", System.currentTimeMillis()));
        service.cast(new Vote("v3", "Bob", System.currentTimeMillis()));

        // 5️⃣ Choix stratégie par argument CLI
        CountingStrategy strategy = chooseStrategy(args);

        // 6️⃣ Calcul des résultats
        Map<String, Integer> results = service.count(strategy);

        // 7️⃣ Affichage résultats
        System.out.println("=== RESULTS ===");
        results.forEach((candidate, count) ->
                System.out.println(candidate + " : " + count));
    }

    private static CountingStrategy chooseStrategy(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("plurality")) {
            return new PluralityCountingStrategy();
        }

        // default
        return new PluralityCountingStrategy();
    }
}
