package com.slusarz.worksupport.module.logdownloader.domain;

import java.util.ArrayList;
import java.util.List;

public class Buffer {

    private List<String> lines = new ArrayList<>();

    private int size;

    public Buffer(int size) {
        this.size = size;
    }

    public boolean flushBuffer() {
        return lines.size() == size;
    }

    public void add(String line) {
        if (size != 0) {
            if (lines.size() >= size) {
                lines.remove(0);
            }
            lines.add(line);
        }
    }

    public List<String> pop() {
        List<String> lines = new ArrayList<>(this.lines);
        this.lines.clear();
        return lines;
    }


}
