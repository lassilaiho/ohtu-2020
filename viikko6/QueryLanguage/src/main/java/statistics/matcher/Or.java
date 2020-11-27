package statistics.matcher;

import statistics.Player;

public class Or implements Matcher {
    private Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player player) {
        for (var matcher : matchers) {
            if (matcher.matches(player)) {
                return true;
            }
        }
        return false;
    }
}
