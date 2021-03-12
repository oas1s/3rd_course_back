package com.rat.squad.storage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    boolean deleted;
    String actualName;
    String mimeType;
    Long size;
    @Lob
    private Blob data;

}
