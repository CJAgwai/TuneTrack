package com.heroes.api.heroesapi.persistence;

import java.io.IOException;
import com.heroes.api.heroesapi.model.Entry;

/**
 * Defines the interface for Entry object persistence
 * 
 * @author SWEN Faculty
 */
public interface EntryDAO {
    /**
     * Retrieves all {@linkplain Entry entries}
     * 
     * @return An array of {@link Entry entry} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Entry[] getEntries() throws IOException;

    /**
     * Finds all {@linkplain Entry entries} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Entry entries} whose nemes contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Entry[] findEntries(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Entry entry} with the given id
     * 
     * @param id The id of the {@link Entry entry} to get
     * 
     * @return a {@link Entry entry} object with the matching id
     * <br>
     * null if no {@link Entry entry} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Entry getEntry(int id) throws IOException;

    /**
     * Creates and saves a {@linkplain Entry entry}
     * 
     * @param entry {@linkplain Entry entry} object to be created and saved
     * <br>
     * The id of the Entry object is ignored and a new uniqe id is assigned
     *
     * @return new {@link Entry entry} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Entry createEntry(Entry entry) throws IOException;

    /**
     * Updates and saves a {@linkplain Entry entry}
     * 
     * @param {@link Entry entry} object to be updated and saved
     * 
     * @return updated {@link Entry entry} if successful, null if
     * {@link Entry entry} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Entry updateEntry(Entry entry) throws IOException;

    /**
     * Deletes a {@linkplain Entry entry} with the given id
     * 
     * @param id The id of the {@link Entry entry}
     * 
     * @return true if the {@link Entry entry} was deleted
     * <br>
     * false if entry with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteEntry(int id) throws IOException;
}
