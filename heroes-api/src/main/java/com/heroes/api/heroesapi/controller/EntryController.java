package com.heroes.api.heroesapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.heroes.api.heroesapi.persistence.EntryDAO;
import com.heroes.api.heroesapi.model.Entry;

/**
 * Handles the REST API requests for the Entry resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 */

@RestController
@RequestMapping("entries")
public class EntryController {
    private static final Logger LOG = Logger.getLogger(EntryController.class.getName());
    private EntryDAO entryDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param entryDao The {@link EntryDAO Entry Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public EntryController(EntryDAO entryDao) {
        this.entryDao = entryDao;
    }

    /**
     * Responds to the GET request for a {@linkplain Entry entry} for the given id
     * 
     * @param id The id used to locate the {@link Entry entry}
     * 
     * @return ResponseEntity with {@link Entry entry} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Entry> getEntry(@PathVariable int id) {
        LOG.info("GET /entries/" + id);
        try {
            Entry entry = entryDao.getEntry(id);
            if (entry != null)
                return new ResponseEntity<Entry>(entry,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Entry entries}
     * 
     * @return ResponseEntity with array of {@link Entry entry} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Entry[]> getEntries() {
        LOG.info("GET /entries");

        try {
            Entry[] entries = entryDao.getEntries();
            if (entries != null)
            return new ResponseEntity<Entry[]>(entries, HttpStatus.OK);
            else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Entry entries} whose title contains
     * the text in name
     * 
     * @param title The name parameter which contains the text used to find the {@link Entry entries}
     * 
     * @return ResponseEntity with array of {@link Entry entries} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all entries that contain the text "ma"
     * GET http://localhost:8080/entries/?name=ma
     */
    @GetMapping("/")
    public ResponseEntity<Entry[]> searchEntries(@RequestParam String title) {
        LOG.info("GET /entries/?title="+title);

        try {
            
            Entry[] entries = entryDao.findEntries(title);
            if (entries.length != 0)
                return new ResponseEntity<Entry[]>(entries, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a {@linkplain Entry entry} with the provided entry object
     * 
     * @param entry - The {@link Entry entry} to create
     * 
     * @return ResponseEntity with created {@link Entry entry} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Entry entry} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Entry> createEntry(@RequestBody Entry entry) {
        LOG.info("POST /entries " + entry);
        try {
            Entry new_entry = entryDao.createEntry(entry);

            if (new_entry == null) {
                // Return 409 CONFLICT if creation failed
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            
            return new ResponseEntity<Entry>(new_entry, HttpStatus.CREATED);
            
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    /**
     * Updates the {@linkplain Entry entry} with the provided {@linkplain Entry entry} object, if it exists
     * 
     * @param entry The {@link Entry entry} to update
     * 
     * @return ResponseEntity with updated {@link Entry entry} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Entry> updateEntry(@RequestBody Entry entry) {
        LOG.info("PUT /entries " + entry);

        try {
            Entry updatedEntry = entryDao.updateEntry(entry);

            if (updatedEntry == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(updatedEntry, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    /**
     * Deletes a {@linkplain Entry entry} with the given id
     * 
     * @param id The id of the {@link Entry entry} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Entry> deleteEntry(@PathVariable int id) {
        LOG.info("DELETE /entries/" + id);

        try{
            boolean result = entryDao.deleteEntry(id);
            if (result == false)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
