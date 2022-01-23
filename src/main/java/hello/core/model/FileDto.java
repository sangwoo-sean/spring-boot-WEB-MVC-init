package hello.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class FileDto {

    private String fileName;
    private String contentType;
    private String url;
    private Long size;

    public FileDto() {}

    public FileDto(String fileName, String contentType, Long size) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
    }
}
