package com.slusarz.worksupport.tenant.application.ssh;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.ssh.application.connection.SshConnectionPropertiesProvider;
import com.slusarz.worksupport.ssh.domain.conection.SshConnectionProperties;
import com.slusarz.worksupport.tenant.application.environment.EnvironmentTenantContext;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class TenantSshConnectionProvider implements SshConnectionPropertiesProvider {

    public Map<Environment, SshConnectionProperties> sshProperties;

    @Override
    public SshConnectionProperties getSshConnectionProperties() {
        return sshProperties.get(EnvironmentTenantContext.getCurrentTenant());
    }
}
