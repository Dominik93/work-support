package com.slusarz.worksupport.module.logdownloader.application.downloader.url;

import com.slusarz.worksupport.commontypes.application.CloseableUtil;
import com.slusarz.worksupport.commontypes.application.loggable.Loggable;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.UrlDownloadRequest;
import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;
import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import com.slusarz.worksupport.module.logdownloader.domain.file.UrlMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.Future;

@Slf4j
@Component
@Loggable
public class UrlDownloader {

 /*   @Autowired
    private Authentication authentication;
*/
    @Async
    public Future<Void> downloadAsync(UrlDownloadRequest request) {
        download(request);
        return new AsyncResult<>(null);
    }

    public void download(UrlDownloadRequest urlDownloadRequest) {
        try {
            // authentication.authorize(); TODO FIXME

            FtpMetadata destinationArchive = urlDownloadRequest.getDestinationArchive();
            UrlMetadata sourceArchive = urlDownloadRequest.getSourceArchive();
            log.info("Try download file form URL");
            log.info("source " + sourceArchive.toFullURL().toString());
            log.info("destination " + destinationArchive.getFullPath());
            ReadableByteChannel readableByteChannel = Channels.newChannel(sourceArchive.toFullURL().openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(destinationArchive.getName().getName());
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Async
    public Future<CurrentLogFile> getAsync(UrlMetadata sourceFile) {
        return new AsyncResult<>(get(sourceFile));
    }

    public CurrentLogFile get(UrlMetadata sourceFile) {
        try {

           // authentication.authorize();
            log.info("Try download file form URL");
            log.info("source " + sourceFile.toFullURL().toString());
            byte[] content;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream is = null;
            try {
                is = sourceFile.getUrl().openStream();
                byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
                int n;

                while ((n = is.read(byteChunk)) > 0) {
                    baos.write(byteChunk, 0, n);
                }
                content = baos.toByteArray();
            } catch (IOException e) {
                System.err.printf("Failed while reading bytes from %s: %s", sourceFile.getUrl().toExternalForm(), e.getMessage());
                throw new RuntimeException(e);
            } finally {
                CloseableUtil.close(baos, is);
            }
            return CurrentLogFile.of(sourceFile.getName(), sourceFile.getServer(), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
