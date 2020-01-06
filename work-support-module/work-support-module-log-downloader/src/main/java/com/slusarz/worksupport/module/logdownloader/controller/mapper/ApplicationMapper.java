package com.slusarz.worksupport.module.logdownloader.controller.mapper;

import com.slusarz.worksupport.module.logdownloader.domain.Tag;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.application.ApplicationName;
import com.slusarz.worksupport.module.logdownloader.specification.model.ApplicationGroup;
import com.slusarz.worksupport.module.logdownloader.specification.model.GetApplicationsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ApplicationMapper {

    public GetApplicationsResponse toApplicationsResponse(final List<Application> applications) {
        Map<Tag, List<Application>> applicationsMap = applications.stream().collect(Collectors.groupingBy(Application::getTag));
        GetApplicationsResponse.GetApplicationsResponseBuilder builder = GetApplicationsResponse.builder();
        for (Map.Entry<Tag, List<Application>> tagListEntry : applicationsMap.entrySet()) {
            builder.group(toApplicationGroup(tagListEntry));
        }
        return builder.build();
    }

    private ApplicationGroup toApplicationGroup(Map.Entry<Tag, List<Application>> tagListEntry) {
        return ApplicationGroup.builder()
                .name(tagListEntry.getKey().getName())
                .applications(tagListEntry.getValue().stream()
                        .map(Application::getApplicationName)
                        .map(ApplicationName::getName)
                        .collect(Collectors.toList()))
                .build();

    }


}
