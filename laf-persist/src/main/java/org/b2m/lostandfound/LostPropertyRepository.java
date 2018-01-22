package org.b2m.lostandfound;

import com.sun.org.apache.bcel.internal.classfile.SourceFile;

import java.util.List;
/**
 * Interfejs LostPropertyRepository wchodzi w skład wzorca projektowego Data Access Object (DAO) i definiuje
 * dozwolone operacje na bazie danych. Takie podejście zapewnia komunikację między aplikacją a źródłem danych
 * zapewniając jednocześnie niezależność od bazy danych.
 */
public interface LostPropertyRepository {

    /**
     * Funkcja dodaje listę przedmiotów do bazy danych.
     * @param lostItemInRepositories lista obiektów reprezentująca przedmioty, które chceby dodać do bazy danych
     */
    void addLostItems(List<ItemInRepository> lostItemInRepositories);

    /**
     * Funkcja dodaje plik źródłowy do bazy danych.
     * @param sourceFileInRepository obiekt reprezentujący plik źródłowy
     */
    void addSourceFile(SourceFileInRepository sourceFileInRepository);
    /**
     * Funkcja dodaje biuro rzeczy znalezionych do bazy danych.
     * @param office obiekt reprezentujący biuro rzeczy znalezionych, które chcemy dodać do bazy danych
     */
    void addLostPropertyOffice(LostPropertyOffice office);

    /**
     * Funkcja zwraca listę przedmiotów z bazy danych na podstawie opisu oraz nazwy miasta.
     * @param itemDescription opis przedmiotu
     * @param cityName nazwa miasta
     * @return lista obiektów reprezentująca przedmioty spełniające kryteria
     */
    List<ItemInRepository> findByItemDescription(String itemDescription, String cityName);

    /**
     * Funkcja zwraca obiekt biura rzeczy znalezionych z bazy danych na podstawie nazwy miasta.
     * @param cityName nazwa miasta
     * @return obiekt reprezentujący biuro rzeczy znalezionych
     */
    LostPropertyOffice findLostPropertyOffice(String cityName);

    /**
     * Funkcja zwraca wszystkie przedmioty z bazy danych, które należą do konkretnego biura rzeczy znalezionych.
     * @param office nazwa biura
     * @return lista obiektów reprezentująca przedmioty z konkretnego biura
     */
    List<ItemInRepository> returnAllItemsFromOffice(String office);

    /**
     * Funkcja zwraca wszystkie przedmioty z bazy danych.
     * @return lista obiektów reprezentująca wszsytkie obiekty w bazie danych
     */
    List<ItemInRepository> returnAllItems();

    /**
     * Funkcja usuwa biuro z bazy danych na podstawie nazwy biura.
     * @param officeName nazwa biura
     */
    void deleteLostPropertyOffice(String officeName);

    /**
     * Funkcja usuwa przedmioty z bazy danych.
     * @param items lista przedmiotów do usunięcia z bazy danych
     */
    void deleteLostItems(List<ItemInRepository> items);

    /**
     * Funkcja zwraca pliki źródłowe z konkretnego biura.
     * @param officeName nazwa biura
     * @return lista obiektów reprezentujących pliki źródłowe
     */
    List<SourceFileInRepository> getSourceFiles(String officeName);

    /**
     * Funkcja zwraca listę przedmiotow powiązanych z konkretnym plikiem źródłowym.
     * @param file plik źródłowy
     * @return lista obiektów reprezentujących przedmioty powiązane z plikiem źródłowym
     */
    List<ItemInRepository> getItemsListedOnSourceFile(SourceFileInRepository file);

    /**
     * Funkcja usuwa przedmioty powiązane z konkretnym plikiem źródłowym z bazy danych.
     * @param file plik źródłowy
     */
    void deleteItemsListedOnSourceFile(SourceFileInRepository file);

    /**
     * Funkcja usuwa plik źródłowy z bazy danych.
     * @param sourceFileInRepository plik
     */
    void deleteSourceFile(SourceFileInRepository sourceFileInRepository);

}

