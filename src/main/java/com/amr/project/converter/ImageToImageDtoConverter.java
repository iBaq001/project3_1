package com.amr.project.converter;

import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.entity.Image;
import lombok.experimental.UtilityClass;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.util.Base64;


@UtilityClass
public class ImageToImageDtoConverter {
    private static ImageDto noPicImageDto;

/*Метод возвращает объект ImageDto, картинка записана в поле String imageInBase64WithMimetypePrefix и представляет
* собой готовую к импорту в html строку вида: "data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTI5IiBo..."*/
    public static ImageDto convertImageToImageDto(Image image) {
        ImageDto result = new ImageDto();

        if (image == null || image.getMimeType() == null) { //если картинки нет, то вставляем картинку NOLOGO
            return returnPictureWithNoLogo();
        }

        result.setId(image.getId());
        result.setIsMain(image.getIsMain());
        result.setImageInBase64WithMimetypePrefix(
                "data:" + image.getMimeType() + ";base64," + Base64.getEncoder().encodeToString(image.getPicture()));
        return result;
    }

    /*Метод возвращает картинку с надписью No logo*/
    private static ImageDto returnPictureWithNoLogo() {
        if(noPicImageDto == null) {
            File file1 = new File("src/main/resources/static/images/No-logo.png");
            Image image1 = new Image(file1);
            image1.setIsMain(true);
            noPicImageDto = convertImageToImageDto(image1);
        }
        return noPicImageDto;
    }
}
