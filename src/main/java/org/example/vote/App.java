package org.example.vote;

import org.example.vote.factory.RepositoryFactory;
import org.example.vote.model.Vote;
import org.example.vote.observer.LoggingVoteListener;
import org.example.vote.service.VoteService;
import org.example.vote.strategy.CountingStrategy;
import org.example.vote.strategy.PluralityCountingStrategy;

import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        // Repository via Factory
        var repo = RepositoryFactory.createRepo("memory");

        // Service
        VoteService service = new VoteService(repo);

        // Observer
        service.addListener(new LoggingVoteListener());

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to VotingApp!");
        System.out.println("Commands: vote | count | reset | exit");

        while (true) {
            String cmd = sc.nextLine().trim().toLowerCase();

            switch (cmd) {

                case "vote" -> {
                    System.out.println("Enter voter name:");
                    String voter = sc.nextLine();

                    System.out.println("Enter candidate:");
                    String candidate = sc.nextLine();

                    service.cast(new Vote(
                            voter,
                            candidate,
                            System.currentTimeMillis()
                    ));
                }

                case "count" -> {
                    CountingStrategy strategy =
                            chooseStrategy(args);

                    Map<String, Integer> results =
                            service.count(strategy);

                    System.out.println("=== RESULTS ===");
                    results.forEach((c, v) ->
                            System.out.println(c + " => " + v));
                }

                case "reset" -> {
                    service.reset();
                    System.out.println("Reset done");
                }

                case "exit" -> {
                    System.out.println("Bye!");
                    sc.close();
                    return;
                }

                default -> System.out.println("Unknown command");
            }
        }
    }

    private static CountingStrategy chooseStrategy(String[] args) {
        if (args.length > 0 &&
                args[0].equalsIgnoreCase("plurality")) {
            return new PluralityCountingStrategy();
        }
        return new PluralityCountingStrategy(); // default
    }
}
