package com.slusarz.worksupport.module.logdownloader.domain.downloader;

import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import lombok.Value;

@Value(staticConstructor = "of")
public class SftpDownloadRequest {

    private FtpMetadata sourceFile;

    private FtpMetadata destinationFile;

}
