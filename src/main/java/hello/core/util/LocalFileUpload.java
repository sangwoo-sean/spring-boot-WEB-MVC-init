package hello.core.util;

import hello.core.model.FileDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Component
public class LocalFileUpload implements FileUpload {

    private final String filePath = "/static/file/";

    @Override
    public FileDto save(MultipartFile file) throws IOException {
        if (file.isEmpty()) return null;
        FileDto fileDto = new FileDto(file.getOriginalFilename(), file.getContentType(), file.getSize());
        File newFileName = new File(UUID.randomUUID() + "_" + fileDto.getFileName());
        file.transferTo(newFileName); // 파일 저장
        fileDto.setUrl(filePath + newFileName);
        return fileDto;
    }

    @Override
    public ArrayList<FileDto> save(MultipartFile[] files) throws IOException {
        return save(new ArrayList<>(Arrays.asList(files)));
    }

    @Override
    public ArrayList<FileDto> save(ArrayList<MultipartFile> files) throws IOException {
        ArrayList<FileDto> fileDtos = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            fileDtos.add(save(multipartFile));
        }
        return fileDtos;
    }
}
