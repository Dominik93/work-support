package com.slusarz.worksupport.module.logdownloader.application.archive;

import com.slusarz.worksupport.module.logdownloader.domain.file.Archive;
import com.slusarz.worksupport.module.logdownloader.domain.file.BytesToZip;
import com.slusarz.worksupport.module.logdownloader.domain.file.FileToZip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
public class ArchiveWriterService {

    public void writeFilesToZip(Archive archive, List<FileToZip> files) throws IOException {
        FileOutputStream fos = new FileOutputStream(archive.getFullPath().getPath());
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (FileToZip file : files) {
            File fileToZip = new File(Paths.get(file.getFullPath().getPath()).toUri());
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(file.getServer().getDir() + "_" + fileToZip.getName() + ".log");
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
    }

    public void writeBytesToZip(Archive archive, List<BytesToZip> files) throws IOException {
        FileOutputStream fos = new FileOutputStream(archive.getFullPath().getPath());
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (BytesToZip file : files) {
            ZipEntry zipEntry = new ZipEntry(file.getName().getName());
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            InputStream inputStream = new ByteArrayInputStream(file.getBytes());
            while ((length = inputStream.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            inputStream.close();
        }
        zipOut.close();
        fos.close();
    }

}
