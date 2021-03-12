package com.rat.squad.storage.controller;

import com.rat.squad.storage.dto.ControllerResult;
import com.rat.squad.storage.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DataController {

    private final DataService dataService;

    @PostMapping(value = "/data", consumes = "multipart/*")
    public ControllerResult handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return dataService.save(file);
    }

    @PostMapping("/data")
    public ControllerResult handleTextUpload(HttpServletRequest request) throws IOException {
        return dataService.save( request.getInputStream(),"plain/text", request.getContentLengthLong(),
                LocalDateTime.now().toString() + "-text.txt");
    }

    @GetMapping("/data/{id}")
    public void handleFileDownload(@PathVariable("id") Long id, HttpServletResponse response) throws IOException, SQLException {
        dataService.get(id, response);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IOException.class, SQLException.class})
    public ControllerResult handleFileUploadError(Exception e) {
        return ControllerResult.failResult(e.getMessage());

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public ControllerResult handleNotFoundError(Exception e) {
        return ControllerResult.failResult(e.getMessage());

    }
}
