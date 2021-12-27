package me.djamelkorei.projecttaskmanagement.web.rest;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.storage.StorageFileNotFoundException;
import me.djamelkorei.projecttaskmanagement.storage.StorageService;
import me.djamelkorei.projecttaskmanagement.util.RandomUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * REST controller for managing file upload.
 *
 * @author Djamel Eddine Korei
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileUploadResource {

  private final StorageService storageService;

  @GetMapping("/files/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
    Resource file = storageService.loadAsResource(filename);
    return ResponseEntity
      .ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
      .body(file);
  }

  @PostMapping("/files")
  public ResponseEntity<Map> handleFileUpload(@RequestParam("file") MultipartFile file) {
    String filename = RandomUtils.generateFileName() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
    storageService.store(file, filename);
    return ResponseEntity.ok(new HashMap<>() {{
      put("name", filename);
    }});
  }

  @ExceptionHandler(StorageFileNotFoundException.class)
  public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
    return ResponseEntity.notFound().build();
  }

}
