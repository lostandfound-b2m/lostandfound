package org.b2m.lostandfound;

import java.io.IOException;
import java.util.List;

/**
 * Zadaniem klasy Retriever jest zebranie informacji ze stron biur rzeczy znalezionych
 * na temat dostępnch plików (np *.pdf, *.html) z listami znalezionych rzeczy. Następnie,
 * umożliwia uzyskanie listy rzeczy, które znajdują się w tych plikach.
 */
public interface Retriever {
    /**
     * Funkcja scrapuje strony główne biur rzeczy znalezionych w poszukiwaniu linków
     * do plików z listami znalezionych rzeczy. Przy analizie stron
     * wykorzystywana jest znajomość charakterystycznych
     * nazwy plików i używanych na stonie rozszerzeń plików.
     * @return lista plików znajdujących się na stronie danego biura
     * @throws IOException gdy nie udaje się otworzyć strony lub jej przeanalizować
     */
    List<SourceFile> retrieveFiles() throws IOException;

    /**
     * Funkcja wywołuje Parser odpowiedni dla danego biura dla wszystkich plików
     * w liście podanej jako parametr i zwraca listę ze znalezionych rzeczami
     * wylistowanymi na wszystkich tych plikach.
     * @param files lista plików, z których chcemy odczytać znalezione rzeczy
     * @return lista znalezionych rzeczy
     * @throws IOException gdy nie udaje się pobrać co najmniej jednego z podanych plików
     */
    List<Item> retrieveItemsFromFiles(List<SourceFile> files) throws IOException;
    String getOfficeName();

}
