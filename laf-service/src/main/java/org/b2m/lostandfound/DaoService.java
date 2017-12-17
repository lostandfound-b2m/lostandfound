package org.b2m.lostandfound;

import java.util.List;

public class DaoService {
    /* zwraca wszystkie obiekty SourceFile powiązane z danym biurem */
    List<SourceFile> getSourceFiles(String officeName) {
        return null;
    }

    void addSourceFiles(SourceFile sourceFile) {}

    /* usuwa z bazy danych aktualnie znajdujące się tam SourceFile */
    void deleteSourceFile(SourceFile sourceFile) {}

    /* uaktualnia podany SourceFile, czy będzie to po prostu kolejne
     * wywołanie metod deleteSourceFile i addSourceFile, czy zroione jakoś sprytniej,
     * to Twoja decyzja Michał. Będziemy wywoływać tę metodę np. gdy plik reprezentowany
     * przez SourceFile został zmodyfikowany, czyli z punktu widzenia obiektu, zmieniło
     * się jedynie jego jedno pole: updateChecker. Być może da się w bazie danych podmienić
     * tylko ten jeden atrybut, zamiast usuwać cały rekord i wstawiać od nowa, ale
     * nie wiem, bo się na tym nie znam :D
    */
    void updateSourceFile(SourceFile sourceFile) {}

    /* zwraca listę Itemów powiązanych z podanym sourceFile */
    List<Item> getItemsListedOnSourceFile(SourceFile sourceFile) {
        return null;
    }

    /* usuwa wszystkie Itemy powiązane z podanym sourceFile */
    void deleteItemsListedOnSourceFile(SourceFile sourceFile) {}

    void addItems(List<Item> itemList) {}
}
