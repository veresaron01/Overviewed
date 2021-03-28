package amoeba;

public class View_ConsoleTexts {

    public void printGameIntro() {
        System.out.println("\n\nWelcome to the Amoeba Game!\n");
        System.out.println("You have to make a line of 4 with your character (x/o).\n");
        System.out.println("The line can be horizontal, vertical, or diagonal.\n");
    }

    public void printWrongFieldDimensionSizes() {
        System.out.println("Choose dimension sizes (4-8)!\n First: a number for height + enter \n Second: a number for width + enter");
    }

    //    Van itt pár olyan print metódus ami 99%-ban ugyanazt a szöveget írja ki.
    //    Azt javaslom legyen 1 metódus ami input paraméterként kapja meg azt az 1 pici részt ami változik a textben.
    //    És behelyettesíti a szövegbe amit kiír. Pl a playser 1 v 2 itt a szám.
    public static void printFirstPlayerChooses() {
        System.out.println("Player #1`s turn. First choose a row with a number and enter, than the column with a number and enter.");
    }

    public static void printSecondPlayerChooses() {
        System.out.println("Player #2`s turn. First choose a row with a number and enter, than the column with a number and enter.");
    }

    public static void printWrongCoordinates() {
        System.out.println("Choose empty coordinates.");
    }

    public static void printWinner1() {
        System.out.println("\n\nThe Winner is Player #1!!! (\"X\") Congratulations!");
    }

    public static void printWinner2() {
        System.out.println("\n\nThe Winner is Player #2!!! (\"O\") Congratulations!");
    }

    public static void printDraw() {
        System.out.println("No Player won, draw-match.");
    }
}
