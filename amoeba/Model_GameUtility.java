package amoeba;

import java.util.*;

public class Model_GameUtility {

    // Az idea ad tanácsokat ahol egy értékadás teljesen felesleges.
    // Több helyen ha egérrel ráhoverezel ahol változónak adsz valami sosem használt alap értéket,
    // kiiírja hogy "initializer is redundant".
    // Az összes változó a classban legyen private. Csak konstansokat hagyjunk így simán elérhetőnek más classok számára.
    // Minden más változó legyen private. Ha ezen a classon kívül akarunk hozzáférni, akkor getterekkel kérjük el innen.
    int fieldDimensionY = 0;
    int fieldDimensionX = 0;
    int fieldSize = 0;

    // Ez a 4 köv változó szerintem teljesen felesleges. Jobban megézve, nem befolyásolják a program működésést.
    // Kitörölve őket, ugyanúgy működik.
    int[] yCoordinates;// = new int[fieldSize];
    static int yCounter = 0;
    int[] xCoordinates;// = new int[fieldSize];
    static int xCounter = 0;

    char[] steps;// = new char[fieldSize];
    // Java coding convention, hogy a static változókat írjuk legfelülre a változók közt.
    static int stepCounter = 0;

    List<Integer> exCoordinatesY = new ArrayList();
    List<Integer> exCoordinatesX = new ArrayList();
    List<Integer> ooCoordinatesY = new ArrayList();
    List<Integer> ooCoordinatesX = new ArrayList();
    List<Integer> allCoordinatesY = new ArrayList();
    List<Integer> allCoordinatesX = new ArrayList();
    List<String> allStepsInString = new ArrayList();

    public Model_GameUtility(int fieldDimensionY, int fieldDimensionX) {
        this.fieldDimensionY = fieldDimensionY;
        this.fieldDimensionX = fieldDimensionX;
        this.fieldSize = fieldDimensionY * fieldDimensionX;
        // Ha egy műveletet többször elvégzel, akkor azt változóból szedd ki inkább.
        // Pl itt a szorzást elmented egy változóba, de utána még többször végrehajtod.
        yCoordinates = new int[fieldDimensionY * fieldDimensionX];
        xCoordinates = new int[fieldDimensionY * fieldDimensionX];
        steps = new char[fieldDimensionY * fieldDimensionX];
    }

    public boolean checkDrawGame() {
        // Ezt egyszerűsítsük így.
        // Sőt, ez a metódus ahol hívva van, ott mindenhol egyből neglva van a return értéke.
        // Jobb volna a negálást átmozgatni ide a returnbe.
        return steps.length == fieldSize;
    }

    public boolean checkValidity(int y, int x) {
        // Használjuk itt is konzisztensen a String.format-ot, mint máshol
        String val = String.format("%s%s", y, x);
        if (allStepsInString.contains(val) || y > fieldDimensionY || x > fieldDimensionX) {
            return false;
        }
        allStepsInString.add(val);
        // Ez a metódus ahol hívva van, ott mindenhol egyből neglva van a return értéke.
        // Jobb volna a negálást elhagyni és itt megfordítani a return true/false-t.
        // Aztán lehetne a metódus neve mondjuk checkInvalidity. Ezzel fölösleges műveletet hagyunk el.
        return true;
    }

    //    addOoStep és addExStep tartalmaz kicsi duplikációt, amit érdemes kiemelni külön metódusba.
    public void addOoStep(int y, int x) {
        ooCoordinatesY.add(y);
        ooCoordinatesX.add(x);

        yCoordinates[yCounter] = y;
        yCounter++;

        xCoordinates[xCounter] = x;
        xCounter++;

        allCoordinatesY.add(y);
        allCoordinatesX.add(x);

        steps[stepCounter] = 'O';
        stepCounter++;
    }

    public void addExStep(int y, int x) {
        exCoordinatesY.add(y);
        exCoordinatesX.add(x);

        yCoordinates[yCounter] = y;
        yCounter++;

        xCoordinates[xCounter] = x;
        xCounter++;

        allCoordinatesY.add(y);
        allCoordinatesX.add(x);
        // Elég redundánsnak tűnik ez a steps[stepCounter] =... mivel az értékeet amit belerakunk sehol sem nézzük többé.
        steps[stepCounter] = 'X';
        stepCounter++;
    }

    public char[][] getCurrentWholeField() {
        // Ez a currentWholeField teljesen elveszti az értékét minden alkalommal amikor ezt a metódust hívod.
        // Mindig újra létrehozzuk, feltöltjük az értékét whitespacekkel. Redundáns művelet, ott is eszi a processzort ahol nem is kell.
        // Csak azért, hogy utána majd pár whitespacet az aktuális X és O karakterekkel felülcsapjuk.
        // Azt javaslom a mátrix méret megadás menjek ki a konstruktorba.
        // A 2 for ciklus ami feltölti kezdetleges értékekkel, az is menjen ki valami 1x mehívott setUpField vagy ilyesmi metódusba.
        char[][] currentWholeField = new char[fieldDimensionY][fieldDimensionX];

        for (int i = 0; i < fieldDimensionY; i++) {
            for (int j = 0; j < fieldDimensionX; j++) {
                currentWholeField[i][j] = ' ';
            }
        }

        // A köv 2 for ciklus elég hasonlóan néz ki. Ki lehetne emelni a duplikációt egy metódusba.
        // Aminek lehetne 2 input paramétere. A lista amin iterál, meg az X/O.
        for (int i = 0; i < exCoordinatesY.size(); i++) {
            currentWholeField[exCoordinatesY.get(i)][exCoordinatesX.get(i)] = 'X';
        }

        for (int i = 0; i < ooCoordinatesY.size(); i++) {
            currentWholeField[ooCoordinatesY.get(i)][ooCoordinatesX.get(i)] = 'O';
        }

        return currentWholeField;
    }

    public static boolean continuity(int[] a) {
        boolean result = true;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != a[i + 1] - 1) {
                // Ha az if itt teljesült, a ciklus még mindig pörög tovább fölöslegesen. Mehetne egyből return false az ifbpl.
                result = false;
            }
        }
        //  Ez lehetne return true, és akkor nincsszükség a result fáltozóra. Ha kevesebb változónk van, kevesebb memóriát eszik a program.
        return result;
    }

    // dead code, redundáns metódus
    public static boolean continuity(List<Integer> a) {
        boolean result = true;
        for (int i = 0; i < a.size() - 1; i++) {
            if (a.get(i) != a.get(i + 1) - 1) {
                result = false;
            }
        }
        return result;
    }

    //talalatok megtalalasa (4 darab egy egyenes menten megszakitas nelkul, egy fajtabol (X=1 || O=2)
    // Ez a metódus nagyon hosszú. Nem szabad túl hosszú metódusokat írni, nehezíti a program átláthatóságát és bővíthetőségét.
    // Azt javaslom, hogy ha egy metódus elkezd 50 sor fölé kúszni, daraboljuk. A logikailag elkülöníthető részei menjenek külön metdusokba.
    // Pl: vízszintes matchelés 1 metódus, függőleges 1 másik.
    public boolean matcher(int XO) {
        List<Integer> YCoordinatesForXOrOSteps = new ArrayList();
        List<Integer> XCoordinatesForXOrOSteps = new ArrayList();

        boolean result = false;
        if (XO == 1) {
            YCoordinatesForXOrOSteps = exCoordinatesY;
            XCoordinatesForXOrOSteps = exCoordinatesX;
        } else if (XO == 2) {
            // Ha úgy írod meg a kódot, hogy csak 1 vagy 2 input paraméterrel hívod meg a matcher metódust, akkor fölösleges az else ifben az ==2 check.
            YCoordinatesForXOrOSteps = ooCoordinatesY;
            XCoordinatesForXOrOSteps = ooCoordinatesX;
        }

        //vizszintesen talalat
        //  A köv 2 sor leegyszerűsíthető: List<Integer> list1 = new ArrayList(YCoordinatesForXOrOSteps);
        List<Integer> list1 = new ArrayList();
        list1.addAll(YCoordinatesForXOrOSteps);//exCoordinatesY
        Collections.sort(list1);

        for (int i = 0; i < list1.size(); i++) {

            int continuityCounterY = 0;
            int[] forCheckConty = new int[4];

            for (int j = 0; j < list1.size(); j++) {
                // Ha nem primitív változókat hasonlítunk össze, hanem pl Integer objecteket, akkor == helyett equals-t használjunk.
                // Az idea is kiírja. Szerencsére itt most működött, de objektumknál a == vezethet téves matcheléshez.
                if (list1.get(i) == list1.get(j) && continuityCounterY < 4) {
                    forCheckConty[continuityCounterY] = XCoordinatesForXOrOSteps.get(j);
                    continuityCounterY++;
                }

            }
            if (continuityCounterY == 4) {
                Arrays.sort(forCheckConty);
                return continuity(forCheckConty);
            }
        }

        //fuggolegesen talalat
        //  hasonlóan egyszerűsíthető mint a list1 amit fentebb írtam
        List<Integer> list2 = new ArrayList();
        list2.addAll(XCoordinatesForXOrOSteps);
        Collections.sort(list2);

        // A vízszintes és függőleges for ciklusos rész nagy százalékban ugyanaz a logika.
        // Kód duplikációt tudunk kiszűrni ha ezt az egész for ciklusos dolgot kipakoljuk egy külön metódusba, és azt hívjuk 2x.
        // Ennek lenne az input paramétere, hogy melyik listán akarunk iterálni, meg melyik lista kell a continuityhez.
        for (int i = 0; i < list2.size(); i++) {

            int continuityCounterX = 0;
            int[] forCheckContx = new int[4];

            for (int j = 0; j < list2.size(); j++) {
                if (list2.get(i) == list2.get(j) && continuityCounterX < 4) {
                    forCheckContx[continuityCounterX] = YCoordinatesForXOrOSteps.get(j);
                    continuityCounterX++;
                }

            }
            if (continuityCounterX == 4) {
                Arrays.sort(forCheckContx);
                return continuity(forCheckContx);
            }
        }

        //atlosan talalat balfentrol, jobb le.
        List<String> exStepsStr = new ArrayList();
        // Minden egyes input megadás után újra eldobjuk exStepsStr lista eddigi elemeit és újra feltöltjük.
        // jó volna ezt valahova kimozgatni félig. Hogy itt csak bővítsük, de ami már benne van az ne vesszen el.
        for (int i = 0; i < YCoordinatesForXOrOSteps.size(); i++) {
            String exAllStr = (YCoordinatesForXOrOSteps.get(i) + 1) + " " + (XCoordinatesForXOrOSteps.get(i) + 1);
            exStepsStr.add(exAllStr);
        }

        // Az átlós lentről-fentről for ciklusos rész nagy százalékban ugyanaz a logika.
        // Kód duplikációt tudunk kiszűrni ha ezt az egész for ciklusos dolgot kipakoljuk egy külön metódusba, és azt hívjuk 2x.
        // Jobban megnézve, ez a for ciklusos rész elég fölösleges. Minden egyes input megadás után újra létrehozzuk teljesen ugyanazt a listát, a allStepsInTheDiagon-et. Elég lenne ezt a listát 1x létrehozni.
        for (int z = 1; z < fieldDimensionX - 2; z++) {
            for (int i = 1; i < fieldDimensionY - 2; i++) {

//                List<String> allStepsInTheDiagon = new ArrayList();
                // Használjunk inkább konzisztensen String.format-ot, pl String element1 = String.format("%s %s", z, i);

//                String element1 = (z) + " " + (i);
//                String element2 = (z + 1) + " " + (i + 1);
//                String element3 = (z + 2) + " " + (i + 2);
//                String element4 = (z + 3) + " " + (i + 3);
//
//                allStepsInTheDiagon.add(element1);
//                allStepsInTheDiagon.add(element2);
//                allStepsInTheDiagon.add(element3);
//                allStepsInTheDiagon.add(element4);

                // ezeket a változókat fölösleges még 1x eltárolni. Fölösleges memória használat, ha csak 1x vannak használva.
                // Mehet egyből:
                List<String> allStepsInTheDiagon = List.of(
                        String.format("%s %s", z, i),
                        String.format("%s %s", (z + 1), (i + 1)),
                        String.format("%s %s", (z + 2), (i + 2)),
                        String.format("%s %s", (z + 3), (i + 3))
                );
                //  a "%s %s" az ismétlődik, na ezt meg érdemes volna egy konstansba kiemelni
                //  Egyébként nem teljesen értem, miért kell a ciklusban a dimension -2, majd utána a lista kreálásnál manuálisan adjuk meg, hogy z+1,+2,+3. Mehetne a teljes ist gyártás a ciklus iterálással a dimenió számig.
                // // Azért kell így mert az átló hosszától függően több találat is lehetséges az adott átlón és ezeknek külön 4-es lista kell. Az egyik for végigmegy a lehetséges átlókon,
                // // a másik for pedig az egyes átlókon belül megy végig a lehetőségeken. Mindegyik ilyen lehetség 4 elemet kell tartalmazzon.
                // // (Amúgy a fieldDimensionX és fieldDimensionY itt még egymás helyén vannak, megcserélve működik teljesen jól, szombaton ezt tettem fel.)
                if (exStepsStr.containsAll(allStepsInTheDiagon)) {
                    return true;
                }
            }
        }

        //bal lentrol, jobb fel
        for (int z = 1; z < fieldDimensionX - 2; z++) {
            for (int i = fieldDimensionY; i > 3; i--) {

                List<String> allStepsInTheDiagon = new ArrayList();

                String element1 = (z) + " " + (i);
                String element2 = (z + 1) + " " + (i - 1);
                String element3 = (z + 2) + " " + (i - 2);
                String element4 = (z + 3) + " " + (i - 3);

                allStepsInTheDiagon.add(element1);
                allStepsInTheDiagon.add(element2);
                allStepsInTheDiagon.add(element3);
                allStepsInTheDiagon.add(element4);

                if (exStepsStr.containsAll(allStepsInTheDiagon)) {
                    return true;
                }
            }
        }
        // ez itt mindig false, úgyhogy a result változó fölösleges, meheten return false
        return result;
    }
}

