package amoeba;

public class View_GameField {

    public void printField(int height, int length, char[][] fieldValues) {
        // field fölösleges változó, sehol nincs használva.
        // Ilyeneket nem jó a kódban hagyni, nehezíti az átláthatóságát.
        char[][] field = new char[length][height];
        field = fieldValues;

        for (int i = 0; i < height; i++) {
            // Itt a for (int j = 0; j < length; j++) 3x ismétlődik. Meg a ciklusok utáni empty stringes print is.
            // Érdemes egy metódusba kiemelni és azt hívni 3x.
            // Hogy csak egyszer legyen leírva. Duplikáció csökkentés.
            // A metódusnak lehetne 1 string paremétere, amiben megadod, hogy mit írjon ki a ciklusban a print.
            for (int j = 0; j < length; j++) {
                System.out.print("_____   ");
            }
            System.out.println("");
            for (int j = 0; j < length; j++) {
                System.out.print("| " + fieldValues[i][j] + " |   ");
            }
            System.out.println("");
            for (int j = 0; j < length; j++) {
                System.out.print("|___|   ");
            }
            System.out.println("");
        }

    }


}
