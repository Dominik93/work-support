package com.slusarz.worksupport.module.logdownloader.application.downloader.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.SftpDownloadRequest;
import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelExecutable;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelResult;
import com.slusarz.worksupport.ssh.domain.channel.VoidSftpChannelResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class SftpArchiveFileDownloader implements SftpChannelExecutable {

    private SftpDownloadRequest sftpDownloadRequest;

    @Override
    public SftpChannelResult execute(final ChannelSftp channelSftp) {
        downloadArchive(channelSftp);
        return VoidSftpChannelResult.empty();
    }

    private void downloadArchive(final ChannelSftp channelSftp) {
        try {
            FtpMetadata sourceFile = sftpDownloadRequest.getSourceFile();
            FtpMetadata destinationFile = sftpDownloadRequest.getDestinationFile();
            log.info("Try download file form sftp");
            log.info("source " + sourceFile.getFullPath());
            log.info("destination " + destinationFile.getFullPath());

            channelSftp.get(sourceFile.getFullPath(), destinationFile.getFullPath());
        } catch (SftpException e) {
            log.info(e.getMessage());
            //throw new LogModuleRuntimeException(e.getMessage(), e, ErrorCode.builder().code(Code.of("SFTP_EXCEPTION")));
        }
    }

}
