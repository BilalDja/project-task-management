package me.djamelkorei.projecttaskmanagement.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * StorageService
 *
 * @author Djamel Eddine Korei
 */
public interface StorageService {

    /**
     * Initialize storage service
     */
    void init();

    /**
     * Store the file
     *
     * @param file     the object of the file
     * @param filename the name of the file
     */
    void store(MultipartFile file, String filename);

    /**
     * Load all files stream
     *
     * @return the stream path
     */
    Stream<Path> loadAll();

    /**
     * Load the path file by filename
     *
     * @param filename the name of the file
     * @return the path
     */
    Path load(String filename);

    /**
     * Load the file as resource by filename
     *
     * @param filename the name of the file
     * @return the resource
     */
    Resource loadAsResource(String filename);

    /**
     * Delete all files
     */
    void deleteAll();

}
