package helpers;

import org.example.Coin;

public class CoinHelper {
    public static Coin getPenny() {
        return new Coin(2.5f, 0.75f);
    }

    public static Coin getNickel() {
        return new Coin(5.0f, 0.84f);
    }

    public static Coin getDime() {
        return new Coin(2.3f, 0.7f);
    }

    public static Coin getQuarter() {
        return new Coin(5.7f, 1f);
    }
}
