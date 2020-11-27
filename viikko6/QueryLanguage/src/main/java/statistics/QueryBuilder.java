package statistics;

import statistics.matcher.*;

public class QueryBuilder {
    private Matcher matcher = new All();

    public QueryBuilder playsIn(String team) {
        matcher = new And(matcher, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        matcher = new And(matcher, new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        matcher = new And(matcher, new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        matcher = new And(matcher, new Or(matchers));
        return this;
    }

    public Matcher build() {
        var m = matcher;
        matcher = new All();
        return m;
    }
}
