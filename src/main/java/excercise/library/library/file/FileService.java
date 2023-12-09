package excercise.library.library.file;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
  private final Path path = Paths.get("uploads");

  public void store(MultipartFile multipartFile) {
    try {
      Path destinationFile = this.path.resolve(Paths.get(multipartFile.getOriginalFilename()))
          .normalize().toAbsolutePath();
      InputStream inputStream = multipartFile.getInputStream();
      Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
      // multipartFile.getBytes(); // mendapatkan ukuran
      // multipartFile.getContentType(); // mendapatkan konten type
    } catch (Exception e) {

    }

  }

  public Resource getFile(String filename) {
    try {
      Path file = path.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (Exception e) {
      System.out.println("ERROR GET FILE :" + e.getMessage());
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  public void init() {
    try {
      Files.createDirectories(path);
    } catch (Exception e) {
    }
  }
}
