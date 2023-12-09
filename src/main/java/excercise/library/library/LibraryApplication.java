package excercise.library.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import excercise.library.library.file.FileService;
import jakarta.annotation.Resource;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {
	@Resource
	FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.fileService.init();
	}

}
