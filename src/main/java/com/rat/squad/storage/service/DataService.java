package com.rat.squad.storage.service;

import com.rat.squad.storage.dto.ControllerResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface DataService {
    ControllerResult save(MultipartFile file) throws IOException;
    void get(Long id,  HttpServletResponse response) throws IOException, SQLException;
}
