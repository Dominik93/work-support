package com.slusarz.worksupport.module.logdownloader.specification.model;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class GetApplicationsResponse {

    @Singular
    private List<ApplicationGroup> groups;

}
