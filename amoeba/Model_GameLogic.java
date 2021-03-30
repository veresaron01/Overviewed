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
        while ((yDim = Integer.parseInt(String.valueOf(controller.getInput()))) < 4 || (xDim = Integer.parseInt(String.valueOf(controller.getInput()))) < 4) {
            // Ez a print és a ciklus itt teljesen fölösleges. Vagy a ciklusban hívott getInput a fölösleges.
            // A getInput addig kéregeti a két számot, amíg 4-8 köz nem lesz.F
            // Emiatt itt az 1. while ciklus nem fog működni rendesen. 1 ciklus elég a számok újrakéregetésére.
            // // Itt is funcionalitása van. A cél az, hogy addig kérje be a két dimenziót amíg nem esik a megfelelő tartományba. Ha valaki a második
            // // inputnál rosszat ad meg, akkor valószínű nem értette meg, nem figyelt a feltételekre, és valószínű az első számot is kijavítaná.
            vct.printWrongFieldDimensionSizes();
        }
        // A getInput-ot ccsak itt hívod, sehol máshol. Itt meg 2x egyből integerré parsolod. Duplikációt szűrne ki, ha egyből int-et adna vissza
        yDim = Integer.parseInt(String.valueOf(cus.getInput()));
        xDim = Integer.parseInt(String.valueOf(cus.getInput()));

        Model_TwoPlayerGame twoPlayerGame = new Model_TwoPlayerGame(yDim, xDim);

        twoPlayerGame.initializeTwoPlayerGame(yDim, xDim);

    }

}
