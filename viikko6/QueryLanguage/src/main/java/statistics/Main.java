package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));

        Matcher m = new And( new HasAtLeast(5, "goals"),
                             new HasAtLeast(5, "assists"),
                             new PlaysIn("PHI")
        );

        printMatches(stats, m);

        var m1 = stats.matches(new And(
            new Not(new HasAtLeast(1, "goals")),
            new PlaysIn("NYR")
        ));
        var m2 = stats.matches(new And(
            new HasFewerThan(1, "goals"),
            new PlaysIn("NYR")
        ));
        assertTrue(m1.toString().equals(m2.toString()));

        assertTrue(stats.matches(new All()).size() == 964);

        printMatches(stats, new Or(
            new HasAtLeast(40, "goals"),
            new HasAtLeast(60, "assists")
        ));

        printMatches(stats, new And(
            new HasAtLeast(50, "points"),
            new Or(
                new PlaysIn("NYR"),
                new PlaysIn("NYI"),
                new PlaysIn("BOS")
            )
        ));

        var query = new QueryBuilder();
        assertTrue(stats.matches(query.build()).size() == 964);

        m = query
            .playsIn("NYR")
            .hasAtLeast(5, "goals")
            .hasFewerThan(10, "goals").build();
        printMatches(stats, m);

        m = query.oneOf(
            query.playsIn("PHI")
                .hasAtLeast(10, "assists")
                .hasFewerThan(5, "goals").build(),
            query.playsIn("EDM")
                .hasAtLeast(40, "points").build()
        ).build();
        printMatches(stats, m);
    }

    private static void printMatches(Statistics stats, Matcher matcher){
        for (var player : stats.matches(matcher)) {
            System.out.println(player);
        }
        System.out.println();
    }

    private static void assertTrue(boolean b){
        if (!b) {
            throw new AssertionError();
        }
    }
}
