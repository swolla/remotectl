import josx.platform.rcx.Sound;

public class Muziek {
    public static final int REIN_OCTAAF = 1;
    public static final int REINE_KWINT = 2;
    public static final int REINE_KWART = 3;
    public static final int GROTE_TERTS = 4;
    public static final int KLEINE_TERTS = 5;
    public static final int GROTE_IETS = 6;
    public static final int KLEINE_IETS = 7;
    public static final int GROTE_SECUNDE = 8;
    public static final int KLEINE_SECUNDE = 9;
    public static final int REINE_HALF = 15;

    public static final int A4 = 432;
    public static final int E4 = omlaag(A4, REINE_KWART);
    public static final int D4 = omlaag(A4, REINE_KWINT);
    public static final int A3 = omlaag(A4, REIN_OCTAAF);
    public static final int E3 = omlaag(A3, REINE_KWART);
    public static final int D3 = omlaag(A3, REINE_KWINT);
    public static final int A2 = omlaag(A3, REIN_OCTAAF);

    public static int omlaag(int frequentie, int sprong) {
        return frequentie - frequentie / (sprong + 1);
    }

    public static int omhoog(int frequentie, int sprong) {
        return frequentie + frequentie / sprong;
    }

    public static void speelLager(int frequentie, int sprong, int duur) {
        Sound.playTone(omlaag(frequentie, sprong), duur);
    }

    public static void speelHoger(int frequentie, int sprong, int duur) {
        Sound.playTone(omhoog(frequentie, sprong), duur);
    }
}
