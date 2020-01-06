package com.slusarz.worksupport.module.logdownloader.application.archive.decompress;

import com.slusarz.worksupport.module.logdownloader.domain.application.Format;
import com.slusarz.worksupport.module.logdownloader.domain.unzip.UnzipRequest;

public interface Decompress {

    void unzip(UnzipRequest unzipRequest);

    Format getFormat();

}
