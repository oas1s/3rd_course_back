package com.rat.squad.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.LobCreator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Helper made for writing and creating blobs
 */
@Service
@RequiredArgsConstructor
public class LobHelper {
    private final SessionFactory sessionFactory;

    public Blob createBlob(InputStream content, long size) {
        return sessionFactory.openSession().getLobHelper().createBlob(content, size);
    }

    @SneakyThrows
    public void writeBLobToOutputStream(Blob data, OutputStream outputStream) {
        IOUtils.copy(data.getBinaryStream(), outputStream);
    }
}