import java.util.Random;

/**
 * Simulation of the 3-door Monty Hall problem, or in short the "Goat" problem, based on the "Let's Make a Deal"
 * television game show.
 * <p>
 * Winning in this game means guessing correctly behind which door a prize (a car) is hidden. Goats are hidden behind
 * all the other doors. What exactly happens in one game, can be seen in the {@link #play()} method.
 * <p>
 * See https://en.wikipedia.org/wiki/Monty_Hall_problem for more details of the game rules.
 */
public class Main {

    private static final Random GOAT_SELECTION_RANDOMIZER = new Random();

    private static final Random PRIZE_POSITION_RANDOMIZER = new Random();

    private static class PlayResults {

        public boolean win1;

        public boolean win2;

        /**
         * @param win1 would player have won by selecting the same 1st door?
         * @param win2 would player have won by selecting the other door?
         */
        public PlayResults(boolean win1, boolean win2) {
            this.win1 = win1;
            this.win2 = win2;
        }

        @Override
        public String toString() {
            return String.format("win1: %s, win2: %s", win1, win2);
        }

    }

    public static void main(String[] args) {
        int wins1 = 0;
        int wins2 = 0;

        for (int i = 0; i < 100; i++) {
            PlayResults res = play();
            System.out.println(String.format("play #%s: %s", 1 + i, res));
            wins1 += res.win1 ? 1 : 0;
            wins2 += res.win2 ? 1 : 0;
        }

        System.out.println(String.format("wins1 (without switch): %s, wins2 (with switch): %s", wins1, wins2));

        // OH, WAIT, isn't `wins2` always simply equal to `100 - wins1`?
        // Yes! See another "OH, WAIT" below :)
    }

    private static PlayResults play() {
        // prize is randomly positioned behind 1 of 3 doors
        int prizePos = 1 + PRIZE_POSITION_RANDOMIZER.nextInt(3);

        // for simplicity, assume player firstly always selects door 1 ("the left most one")
        // therefore host needs to open door 2 or 3, depending on where the prize is really located
        int hostSelection;
        if (prizePos == 1) {
            // host can select randomly from 2 and 3
            hostSelection = 2 + GOAT_SELECTION_RANDOMIZER.nextInt(2);
        } else {
            // host must select the door where prize is not located
            hostSelection = prizePos == 2 ? 3 : 2;
        }

        // possible player's follow-up selections - HERE IS WHERE THE GAME DECIDES!
        // a) still keen on the 1st door
        int playerSelection1 = 1;
        // b) selects the other closed door ("switches")
        int playerSelection2 = hostSelection == 2 ? 3 : 2;

        PlayResults res = new PlayResults(prizePos == playerSelection1, prizePos == playerSelection2);

        // OH, WAIT, isn't this the same as writing down:
        // PlayResults res = new PlayResults(prizePos == playerSelection1, prizePos != playerSelection1);
        //
        // Yes, it is! So in fact, we don't need to know `hostSelection` neither `playerSelection2` to find out
        // what is the better winning strategy:
        //
        // a) Player selects the 1st door => 1/3 CHANCE OF WINNING (Remember there were 3 doors at the beginning?
        // Moreover, the chance that player guessed the correct door remains the same after the host opened the door
        // with a goat.)
        //
        // b) Player selects the other door => 2/3 CHANCE OF WINNING = complement to chance of winning by selecting the
        // 1st door

        return res;
    }

}
