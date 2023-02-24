package com.family.petmemory.infra;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.family.petmemory.entity.memory.Gps;
import com.family.petmemory.entity.memory.ImageSize;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ImageUtil {
    public static LocalDateTime extractLocalDateTime(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

        Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);

        if (date == null) {
            return null;
        }

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Gps extractGps(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        GpsDirectory directory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

        if (directory == null) {
            return null;
        }

        GeoLocation geoLocation = directory.getGeoLocation();
        return new Gps(geoLocation.getLatitude(), geoLocation.getLongitude());
    }

    public static ImageSize extractImageSize(File file) throws IOException {
        BufferedImage bi = ImageIO.read(file);

        return new ImageSize(bi.getWidth(), bi.getHeight());
    }
}
