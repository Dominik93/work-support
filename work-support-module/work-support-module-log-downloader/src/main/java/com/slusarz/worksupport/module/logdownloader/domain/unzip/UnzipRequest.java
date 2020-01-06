package com.slusarz.worksupport.module.logdownloader.domain.unzip;

import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import lombok.Value;

@Value(staticConstructor = "of")
public class UnzipRequest {

    private FtpMetadata source;

    private FtpMetadata destination;

}
