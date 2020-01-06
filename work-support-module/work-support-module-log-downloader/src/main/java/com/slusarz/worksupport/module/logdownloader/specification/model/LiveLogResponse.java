package com.slusarz.worksupport.module.logdownloader.specification.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LiveLogResponse {

    private List<LiveLogEntry> liveLogEntries;

}
