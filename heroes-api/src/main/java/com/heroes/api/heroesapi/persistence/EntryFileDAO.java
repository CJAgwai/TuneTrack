package com.heroes.api.heroesapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import com.heroes.api.heroesapi.model.Entry;

/**
 * Implements the functionality for JSON file-based peristance for Diary Entries
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author SWEN Faculty
 */
@Component
public class EntryFileDAO implements EntryDAO {
    Map<Integer,Entry> entries;   // Provides a local cache of the entry objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between entry
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new entry
    private String filename;    // Filename to read from and write to

    /**
     * Creates a Entry File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public EntryFileDAO(ObjectMapper objectMapper) throws IOException {
        this.filename = "heroes-api\\data\\entries.json";
        this.objectMapper = objectMapper;
        load();  // load the entries from the file
    }

    /**
     * Generates the next id for a new {@linkplain Entry entry}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Entry entries} from the tree map
     * 
     * @return  The array of {@link Entry entries}, may be empty
     */
    private Entry[] getEntriesArray() {
        return getEntriesArray(null);
    }

    /**
     * Generates an array of {@linkplain Entry entries} from the tree map for any
     * {@linkplain Entry entries} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Entry entries}
     * in the tree map
     * 
     * @return  The array of {@link Entry entries}, may be empty
     */
    private Entry[] getEntriesArray(String containsText) { // if containsText == null, no filter
        ArrayList<Entry> entryArrayList = new ArrayList<>();

        for (Entry entry : entries.values()) {
            if (containsText == null || entry.getTitle().contains(containsText)) {
                entryArrayList.add(entry);
            }
        }

        Entry[] entryArray = new Entry[entryArrayList.size()];
        entryArrayList.toArray(entryArray);
        return entryArray;
    }

    /**
     * Saves the {@linkplain Hero entries} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Hero entries} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Entry[] entryArray = getEntriesArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),entryArray);
        return true;
    }

    /**
     * Loads {@linkplain Entry entries} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        entries = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of entries
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Entry[] entryArray = objectMapper.readValue(new File(filename),Entry[].class);

        // Add each entry to the tree map and keep track of the greatest id
        for (Entry entry : entryArray) {
            entries.put(entry.getId(),entry);
            if (entry.getId() > nextId)
                nextId = entry.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Entry[] getEntries() {
        synchronized(entries) {
            return getEntriesArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Entry[] findEntries(String containsText) {
        synchronized(entries) {
            return getEntriesArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Entry getEntry(int id) {
        synchronized(entries) {
            if (entries.containsKey(id))
                return entries.get(id);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Entry createEntry(Entry entry) throws IOException {
        synchronized(entries) {
            // We create a new entry object because the id field is immutable
            // and we need to assign the next unique id
            Entry newEntry = new Entry(nextId(),entry.getTitle(),entry.getArtist(),entry.getYear(),entry.getGenre(),entry.getRating(),entry.getListenDate(),entry.getReview(),entry.getFavoriteSong());
            entries.put(newEntry.getId(),newEntry);
            save(); // may throw an IOException
            return newEntry;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Entry updateEntry(Entry entry) throws IOException {
        synchronized(entries) {
            if (entries.containsKey(entry.getId()) == false)
                return null;  // entry does not exist

            entries.put(entry.getId(),entry);
            save(); // may throw an IOException
            return entry;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteEntry(int id) throws IOException {
        synchronized(entries) {
            if (entries.containsKey(id)) {
                entries.remove(id);
                return save();
            }
            else
                return false;
        }
    }
}
