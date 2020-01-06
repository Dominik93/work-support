package com.slusarz.worksupport.module.logdownloader.application.archive.decompress;

import com.slusarz.worksupport.commontypes.application.CloseableUtil;
import com.slusarz.worksupport.module.logdownloader.domain.application.Format;
import com.slusarz.worksupport.module.logdownloader.domain.unzip.UnzipRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
@Service
public class ZipService implements Decompress {

    @Override
    public void unzip(UnzipRequest unzipRequest) {
        byte[] buffer = new byte[1024];
        ZipInputStream zis = null;
        FileOutputStream fos = null;
        try {
            zis = new ZipInputStream(new FileInputStream(unzipRequest.getSource().getFullPath()));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File file = new File(unzipRequest.getDestination().getFullPath());
                fos = null;
                fos = new FileOutputStream(file);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                CloseableUtil.close(fos);
                zipEntry = zis.getNextEntry();
            }
            CloseableUtil.close(zis);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            CloseableUtil.close(fos);
            CloseableUtil.close(zis);
        }
    }

    @Override
    public Format getFormat() {
        return Format.of(".zip");
    }

}
