package com.rat.squad.storage.service;

import com.rat.squad.storage.dto.ControllerResult;
import com.rat.squad.storage.entity.RawData;
import com.rat.squad.storage.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final DataRepository dataRepository;
    private final LobHelper lobHelper;

    @Override
    public ControllerResult save(MultipartFile file) throws IOException {

        dataRepository.save(RawData.builder()
                .data(lobHelper.createBlob(file.getInputStream(), file.getSize()))
                .deleted(false)
                .actualName(file.getName())
                .mimeType(file.getContentType())
                .size(file.getSize())
                .build());
        return ControllerResult.successResult("file saved");
    }

    @Override
    @Transactional
    public void get(Long id, HttpServletResponse response) throws IOException, SQLException {
        Optional<RawData> optionalOfFound = dataRepository.getFirstByIdAndDeletedFalse(id);
        if (optionalOfFound.isPresent()) {
            RawData found = optionalOfFound.get();
            dataRepository.softDeleteById(id);
            response.setContentType(found.getMimeType());
            lobHelper.writeBLobToOutputStream(found.getData(), response.getOutputStream());
        } else {
            throw new EntityNotFoundException("RawData entity not found");
        }
    }
}
