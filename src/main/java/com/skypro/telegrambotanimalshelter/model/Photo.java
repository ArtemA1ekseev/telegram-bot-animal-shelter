package com.skypro.telegrambotanimalshelter.model;

import javax.persistence.*;

/**
 * Class Photo as a part of daily report from clients
 * <br>
 * Properties: <b>id</b>, <b>filePath</b>, <b>fileSize</b>, <b>mediaType</b>
 * @see Report
 * @version 0.0.1
 * @author i.gatin
 */
@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    private byte[] data;

    @OneToOne
    private Pet pet;
}
