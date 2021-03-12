package com.rat.squad.storage.controller;

import com.rat.squad.storage.dto.ControllerResult;
import com.rat.squad.storage.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class DataController {
    private final DataService dataService;

    @PostMapping("/data")
    public ControllerResult handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        return dataService.save(file);
    }

    @GetMapping("/data/{id}")
    public void handleFileDownload(@PathVariable("id") Long id, HttpServletResponse response) throws IOException, SQLException {
        dataService.get(id, response);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IOException.class, SQLException.class})
    public ControllerResult handleFileUploadError(Exception e){
        return ControllerResult.failResult(e.getMessage());

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public ControllerResult handleNotFoundError(Exception e){
        return ControllerResult.failResult(e.getMessage());

    }
}
