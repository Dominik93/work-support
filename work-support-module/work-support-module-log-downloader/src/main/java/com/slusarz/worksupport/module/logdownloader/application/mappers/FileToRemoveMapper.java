package com.slusarz.worksupport.module.logdownloader.application.mappers;

import com.slusarz.worksupport.filemanager.domain.file.File;
import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.filemanager.domain.file.IFile;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.domain.application.Format;
import com.slusarz.worksupport.module.logdownloader.domain.unzip.UnzipRequest;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FileToRemoveMapper {

    public File toFileToRemove(Path path) {
        FileName fileName = FileName.of(path.getFileName().toString());
        DirectoryPath directoryPath = DirectoryPath.of(path.getParent().toString());
        return File.of(fileName, directoryPath);
    }

    public List<IFile> toFileToRemoves(Map<Format, List<UnzipRequest>> unzipRequests) {
        return unzipRequests.values().stream()
                .flatMap(List::stream)
                .map(this::toFileToRemove)
                .collect(Collectors.toList());
    }

    private File toFileToRemove(UnzipRequest unzipRequest) {
        return File.of(unzipRequest.getSource().getName(),
                DirectoryPath.of(unzipRequest.getSource().getDirectoryPath().getPath()));
    }

}
