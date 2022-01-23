package hello.core.util;

import hello.core.model.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public interface FileUpload {

    FileDto save(MultipartFile file) throws IOException;

    ArrayList<FileDto> save(MultipartFile[] files) throws IOException;

    ArrayList<FileDto> save(ArrayList<MultipartFile> files) throws IOException;
}
