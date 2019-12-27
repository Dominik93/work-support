package com.slusarz.worksupport.tenant.database.application.checker;

import com.slusarz.worksupport.commontypes.domain.TenantConstants;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DatabaseTenantChecker {

    public boolean check(String tenant) {
        return Objects.nonNull(tenant) && !tenant.isEmpty() && tenant.split(TenantConstants.SEPARATOR).length >= 2;
    }

}
