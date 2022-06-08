package com.amr.project.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Objects;

//Картинки будем хранить в БД (для удобства, хотя это и плохая практика)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] picture;

    private String mimeType;

    private Boolean isMain;

    public Image(File file) {
        this();
        this.saveFileAsImage(file);
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*Метод вызывается для объекта Image и сохраняет в нём картинку, переданную в качестве аргумента как объект класса File*/
    public void saveFileAsImage(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            this.setPicture(inputStream.readAllBytes());
            this.setMimeType(URLConnection.guessContentTypeFromName(file.getName()));
        } catch (IOException e) {
            System.out.println("Ошибка чтения изображения: " + e);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Image image = (Image) o;
        return id != null && Objects.equals(id, image.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
