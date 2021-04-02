package amoeba;

import java.io.IOException;

//Java naming conventionben nem rakunk alávonásokat classok nevébe. Camelcase-el írjuk őket, pl: ModelGameLogic
public class Model_GameLogic {

    public void startGame() throws IOException {

        Controller_UserInput cus = new Controller_UserInput();
        // Változóknak mindig mindenhol adj valami meaningful nevet. Munkában elvárás lesz majd.
        // Home projectekben ugye nem, de jó ha megszokod.
        // Valami oylan nevet ami egyértelműen mutatja a változó funkcionalitását. Pl char c helyett char character. t helyett twoPlayerGame, stb
        // Ez fontos, az ilyenekért interjúztatók masszív rossz pontot adnak.
        // update: változtattál rajta, de még mindig van a kódban jópár fura változónév, pl vct, cus
        View_ConsoleTexts vct = new View_ConsoleTexts();

        vct.printGameIntro();
        vct.printWrongFieldDimensionSizes();

        int yDim;
        int xDim;

        // A getInput-ot ccsak itt hívod, sehol máshol. Itt meg 2x egyből integerré parsolod. Duplikációt szűrne ki, ha egyből int-et adna vissza
        yDim = Integer.parseInt(String.valueOf(cus.getInput()));
        xDim = Integer.parseInt(String.valueOf(cus.getInput()));

        Model_TwoPlayerGame twoPlayerGame = new Model_TwoPlayerGame(yDim, xDim);

        twoPlayerGame.initializeTwoPlayerGame(yDim, xDim);

    }

}
