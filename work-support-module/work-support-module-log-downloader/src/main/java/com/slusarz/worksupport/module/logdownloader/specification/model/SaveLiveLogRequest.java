package com.slusarz.worksupport.module.logdownloader.specification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveLiveLogRequest {

    private List<LiveLogEntry> liveLogEntries;

}
