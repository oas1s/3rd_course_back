package com.rat.squad.storage.service;

import com.rat.squad.storage.dto.ControllerResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * DataService interface
 * Methods: 2 save() methods which get different inputs and returns ControllerResult Class, get method to get data by Id
 */
public interface DataService {
    ControllerResult save(MultipartFile file) throws IOException;
    ControllerResult save(InputStream inputStream, String fileType, Long size, String name);
    void get(Long id,  HttpServletResponse response) throws IOException, SQLException;
}
