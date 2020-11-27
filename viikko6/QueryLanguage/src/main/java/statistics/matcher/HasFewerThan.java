package statistics.matcher;

import statistics.Player;

public class HasFewerThan implements Matcher {
    private HasAtLeast hasAtLeast;

    public HasFewerThan(int value, String category) {
        hasAtLeast = new HasAtLeast(value, category);
    }

    @Override
    public boolean matches(Player player) {
        return !hasAtLeast.matches(player);
    }
}
