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
        assert m1.toString().equals(m2.toString());

        assert stats.matches(new All()).size() == 964;

        System.out.println();
        printMatches(stats, new Or(
            new HasAtLeast(40, "goals"),
            new HasAtLeast(60, "assists")
        ));

        System.out.println();
        printMatches(stats, new And(
            new HasAtLeast(50, "points"),
            new Or(
                new PlaysIn("NYR"),
                new PlaysIn("NYI"),
                new PlaysIn("BOS")
            )
        ));
    }

    private static void printMatches(Statistics stats, Matcher matcher){
        for (var player : stats.matches(matcher)) {
            System.out.println(player);
        }
    }
}
