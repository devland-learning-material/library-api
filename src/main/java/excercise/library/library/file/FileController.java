package excercise.library.library.file;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
// @SecurityRequirement(name = "bearerAuth")
public class FileController {
  private final FileService fileService;

  @PostMapping(value = "/files", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
      MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile) {
    this.fileService.store(multipartFile);
    return ResponseEntity.ok("File uploaded successfully");
  }

  @GetMapping(value = "/files/{name}", produces = { MediaType.APPLICATION_PDF_VALUE })
  public Resource getFile(@PathVariable("name") String filename) {
    Resource resource = this.fileService.getFile(filename);
    return resource;
  }

  @GetMapping(value = "/images/{name}", produces = { MediaType.IMAGE_JPEG_VALUE })
  public Resource getImage(@PathVariable("name") String filename) {
    Resource resource = this.fileService.getFile(filename);
    return resource;
  }
}
