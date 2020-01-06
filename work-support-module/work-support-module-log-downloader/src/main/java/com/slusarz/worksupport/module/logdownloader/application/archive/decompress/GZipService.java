package com.slusarz.worksupport.module.logdownloader.application.archive.decompress;

import com.slusarz.worksupport.commontypes.application.CloseableUtil;
import com.slusarz.worksupport.module.logdownloader.domain.application.Format;
import com.slusarz.worksupport.module.logdownloader.domain.unzip.UnzipRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

@Slf4j
@Service
public class GZipService implements Decompress {

    @Override
    public void unzip(UnzipRequest unzipRequest) {
        byte[] buffer = new byte[1024];
        GZIPInputStream gzis = null;
        FileOutputStream out = null;
        try {
            gzis = new GZIPInputStream(new FileInputStream(unzipRequest.getSource().getFullPath()));
            out = new FileOutputStream(unzipRequest.getDestination().getFullPath());
            int len;
            while ((len = gzis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            CloseableUtil.close(gzis, out);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            CloseableUtil.close(gzis, out);
        }

    }

    @Override
    public Format getFormat() {
        return Format.of(".gz");
    }

}
