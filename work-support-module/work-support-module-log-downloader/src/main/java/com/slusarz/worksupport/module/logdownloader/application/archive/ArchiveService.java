package com.slusarz.worksupport.module.logdownloader.application.archive;

import com.slusarz.worksupport.component.async.application.AsyncLoop;
import com.slusarz.worksupport.filemanager.application.remover.FileRemover;
import com.slusarz.worksupport.module.logdownloader.application.archive.decompress.DecompressManager;
import com.slusarz.worksupport.module.logdownloader.application.mappers.FileToRemoveMapper;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.application.Format;
import com.slusarz.worksupport.module.logdownloader.domain.unzip.UnzipRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ArchiveService {

    @Autowired
    @Qualifier("asyncFileRemoverService")
    private FileRemover fileRemover;

    @Autowired
    private DecompressManager decompressManager;

    @Autowired
    private AsyncLoop<UnzipRequest> asyncLoop;

    @Autowired
    private FileToRemoveMapper fileToRemoveMapper;

    @Autowired
    private UnzipRequestsPreparator unzipRequestsPreparator;

    public void unpack(DownloadLog downloadLog) {
        Map<Format, List<UnzipRequest>> unzipRequests = unzipRequestsPreparator.prepareUnzipRequests(downloadLog);
        for (Format format : unzipRequests.keySet()) {
            asyncLoop.invokeAsync(unzipRequests.get(format), decompressManager.get(format)::unzip);
        }
        fileRemover.deleteFiles(fileToRemoveMapper.toFileToRemoves(unzipRequests));
    }

}
