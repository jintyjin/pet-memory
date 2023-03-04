package com.family.petmemory.infra;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.family.petmemory.entity.memory.Gps;
import com.family.petmemory.entity.memory.ImageSize;
import com.family.petmemory.entity.memory.MemoryType;

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

        if (checkNull(directory)) {
            return null;
        }

        Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);

        if (checkNull(date)) {
            return null;
        }

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Gps extractGps(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        GpsDirectory directory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

        if (checkNull(directory)) {
            return null;
        }

        GeoLocation geoLocation = directory.getGeoLocation();
        return new Gps(geoLocation.getLatitude(), geoLocation.getLongitude());
    }

    public static ImageSize extractImageSize(File file, MemoryType memoryType) throws IOException {
        ImageSize imageSize = null;

        if (memoryType == MemoryType.IMAGE) {
            BufferedImage bi = ImageIO.read(file);
            imageSize = new ImageSize(bi.getWidth(), bi.getHeight());
        }

        return imageSize;
    }

    private static boolean checkNull(Object object) {
        if (object == null) {
            return true;
        }
        return false;
    }
}
