package com.rat.squad.storage.service;

import com.rat.squad.storage.dto.ControllerResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public interface DataService {
    ControllerResult save(MultipartFile file) throws IOException;
    ControllerResult save(InputStream inputStream, String fileType, Long size, String name) throws IOException;
    void get(Long id,  HttpServletResponse response) throws IOException, SQLException;
}
