package amoeba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller_UserInput {

    BufferedReader reader;

    public Controller_UserInput() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public char getInputFromConsole() throws IOException {
        char c;
        String s;
        //  Ennek a regexnek itt nem látom hasznát.
        //  Az egész metódus bele lehetne szimplán return reader.readLine().charAt(0);
        // // Megváltozna a program funkcionalitása, ha úgy átírnám. A cél az ezzel, hogy ha valaki több karaktert üt be, akkor annak újabb lehetőséget
        // // adjon a program, mert az nagy eséllyel elgépelés lenne ugyanis sehol nem kér egyszerre több karaktert. (Véletlen 2 billentyű közé üt és 
        // // két vagy több karaktert üt be.)
        while (!(s = reader.readLine()).matches(".")) {
        }
        c = s.charAt(0);
        return c;
    }

    //    Törekszünk mindig a duplikált kód minimalizálására. getStepXInput, getStepOInput ugyanazt csinálja.
    //    Az egyik ráadásul hívva sincs sehol.
    //    Elég az egyik.
    public char getStepXInput(String reg) throws IOException {
        String regex = "[1-" + reg + "]";
        char result = ' ';
        System.out.println(regex);

        while (!String.valueOf((result = getInputFromConsole())).matches(regex)) {
            // Az üzenetek amiket a usernek adunk jó ha minél meaningfulabbak.
            // Pl a szám legyen 1 és valami közt
            System.out.println("Number from 1 - " + reg);
        }

        return result;
    }

    public char getStepOInput(String reg) throws IOException {
        String regex = "[1-" + reg + "]";
        System.out.println(regex);
        // Redundáns inicializálás, az idea ki is írja
        char result = ' ';

        while (!String.valueOf((result = getInputFromConsole())).matches(regex)) {
            System.out.println("Number from 1 - " + reg);
        }

        return result;
    }

    public char getInput() throws IOException {
        // Redundáns inicializálás, az idea ki is írja
        char result = ' ';

        while (!String.valueOf((result = getInputFromConsole())).matches("[4-8]")) {
            System.out.println("Number from 4 - 8");
        }
        return result;
    }

}
