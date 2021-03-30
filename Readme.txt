I have written these programs for practicing Java programming, after more than 3 months of intensive learning.



1. Arabic to Roman and vice versa compiler:
Found in package folder 'numbercompiler'

The program translates roman numbers to arabic numbers and backwards as well. 
Type in the number to the console (if roman, then in capital letters), and the result shows up. 
The program enforces correct syntax, and also has a limit of 5999, or MMMMMCMXCIX.
The program terminates by entering the 'exit' key.



2. Amoeba Game:
Found in package folder 'amoeba'

The program is an implementation of the classic Amoeba game.
Its classes are distributed by the MVC pattern in order to ease the future changes to a more graphical interface.
After start a short introduction for the players shown. The dimensions of the table need to be set, 
and need to be set independently in an arbitrary range of 4 to 8. The program draws out the game table; 
and then Player #1 'X' starts by first choosing the row of the sign, then the coloumn of it. 
Then Player #2 'O' does the same, until someone wins, or a draw game occurs. After each step, 
the freshened table is drawed with the new signs in it. Each of the four winner signs must be the same kind, 
must be on a single line either vertically, horisontally or diagonally. Everything is commented by the program.
With winning or draw game occurence the program terminates.

Általános tippek
* A Readme filenak .md kiterjesztést a szabvány, nem txt. Az .md úgynevezett Markdown, amiben lehet jobban formázni a szöveget.
Illetve a Readme-ben szokás leírni, hogy hogyan lehet a programot elindítani. Pl cmd-ből java paranccsal.
A Readme-ben van pár typo. Az ilyesmire is figyelj. Ha ideaban megnyitod, zölddel aláhúzza neked. Ha cégnél ilyet bemutatsz, azt egyből kiszúrják

* Amit már javasoltam korábban is, a gitignore-t mindig add hozz git repokhoz
Példa gitignore-ra:https://github.com/PietroSassone/flickr-thanker/blob/master/.gitignore

* Ha küldesz kódot amit szeretnéd, hogy mások átnézzenek, arra érdemes Pull requestet csinálni a githubon. Ott jól oda lehet kommentelni adott kódrészlethez a javaslatokat.

*  Egy jól strukturált projektben minden osztály olyan packageben van, ahova funkcionalitása szerint tartozik. Pl: a Model classok a model packageben. Sasold meg flickr-es repomat. Ott is megvan, hogy pageobjects, util, core, stb.

* Az tök jó lett, ahogy a logikát külön classokba raktad funkcionalitás szerint.
Az is tetszik, hogy jól be vannak formázva a classok ahogy kell

* Írtam bele javaslatokat amiktől profibb lesz, de összességében jó kis program.
Szóval ne csüggedj ezen. Én anno 1 év programozás tanulás után sokkal több dolgot elnéztem.

* Következő lépés lehetne a unit tesztek írása, ha akarod még bőviteni a programot.
Az egy fontos és hasznos skill deveknek.