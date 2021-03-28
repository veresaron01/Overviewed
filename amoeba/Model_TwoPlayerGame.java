package amoeba;

import java.io.IOException;

public class Model_TwoPlayerGame {

    // a metódusok neve is legyen valami meaningfulabb.
    // Test nevet teszteknek adjunk, ami tesztel ténlegesen.
    // A név tükrözze, hogym it csinál.
    public void test(int yDim, int xDim) throws IOException {

        Model_GameUtility tpm = new Model_GameUtility(yDim, xDim);

        // Erre a két regexes stringre nincs semmi szükség. Átírhatjuk getStepXInput-ot, hogy int-et kapjon.
        // Adjuk át neki egyből fieldDimensionY, fieldDimensionX értékeket. Ugyanúgy működni fog.
        String forRegexY = String.valueOf(tpm.fieldDimensionY);
        String forRegexX = String.valueOf(tpm.fieldDimensionX);
        // Ez a 2 int teljesen fölöslegesnek tűnik, mivel az értékeket a metódus már megkapta yDim-ben és xDim-ben.
        // Akkor lenne haszna mégegyszer eltárolni, ha utána valami extra műveletet végrehajtunk rajtuk.
        int fieldDimensionY = tpm.fieldDimensionY;
        int fieldDimensionX = tpm.fieldDimensionX;

        Controller_UserInput cl = new Controller_UserInput();
        View_GameField vgf = new View_GameField();

        boolean winnerFound = false;

        vgf.printField(fieldDimensionY, fieldDimensionX, tpm.getCurrentWholeField());

        while (!winnerFound) {
            //first player
            View_ConsoleTexts.printFirstPlayerChooses();

            int y;
            int x;
            // ez kód duplikáció, mehetne egy külön metódusba a while ciklusos rész, amit tudunk 2x is hívni, a két játékosra külön.
            while (!tpm.checkValidity((y = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexY)))) - 1), (x = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexX)))) - 1))) {
                View_ConsoleTexts.printWrongCoordinates();
            }
            tpm.addExStep(y, x);

            char[][] wholeField = tpm.getCurrentWholeField();

            vgf.printField(fieldDimensionY, fieldDimensionX, wholeField);

            if (tpm.matcher(1)) {
                View_ConsoleTexts.printWinner1();
                winnerFound = true;
                continue;
            }
            if (!tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

            //second player
            View_ConsoleTexts.printSecondPlayerChooses();

            int y1;
            int x1;

            while (!tpm.checkValidity((y1 = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexY)))) - 1), (x1 = Integer.parseInt((String.valueOf(cl.getStepXInput(forRegexX)))) - 1))) {
                View_ConsoleTexts.printWrongCoordinates();
            }
            tpm.addOoStep(y1, x1);

            char[][] wholeField1 = tpm.getCurrentWholeField();

            vgf.printField(fieldDimensionY, fieldDimensionX, wholeField1);

            // ez kód duplikáció, mehetne egy külön metódusba aminek van egy paramétere, az 1 vagy a 2
            if (tpm.matcher(2)) {
                View_ConsoleTexts.printWinner2();
                winnerFound = true;
                continue;
            }
            if (!tpm.checkDrawGame()) {
                View_ConsoleTexts.printDraw();
                winnerFound = true;
                continue;
            }

        }
    }

}
