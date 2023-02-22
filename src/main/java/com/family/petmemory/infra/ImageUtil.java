package com.family.petmemory.infra;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.family.petmemory.entity.memory.Gps;

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

        try {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        } catch(NullPointerException e) {
            return null;
        }
    }

    public static Gps extractGps(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        GpsDirectory directory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

        GeoLocation geoLocation = directory.getGeoLocation();

        try {
            return new Gps(geoLocation.getLatitude(), geoLocation.getLongitude());
        } catch(NullPointerException e) {
            return null;
        }
    }
}
