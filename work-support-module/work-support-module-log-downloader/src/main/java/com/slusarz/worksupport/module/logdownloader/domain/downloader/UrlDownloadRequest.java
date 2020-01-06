package com.slusarz.worksupport.module.logdownloader.domain.downloader;

import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import com.slusarz.worksupport.module.logdownloader.domain.file.UrlMetadata;
import lombok.Value;

@Value(staticConstructor = "of")
public class UrlDownloadRequest {

    private UrlMetadata sourceArchive;

    private FtpMetadata destinationArchive;
}
