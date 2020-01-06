package com.slusarz.worksupport.module.logdownloader.application.archive.decompress;

import com.slusarz.worksupport.module.logdownloader.domain.application.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DecompressManager {

    @Autowired
    private List<Decompress> decompresses;

    private Map<Format, Decompress> unzip = new HashMap<>();

    @PostConstruct
    public void init() {
        for (Decompress decompress : decompresses) {
            unzip.put(decompress.getFormat(), decompress);
        }
    }

    public Decompress get(Format format) {
        return unzip.get(format);
    }
}
