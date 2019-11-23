package com.lzt.operate.codetools.service.impl;

import com.lzt.operate.codetools.common.GlobalString;
import com.lzt.operate.codetools.entity.ConnectionConfig;
import com.lzt.operate.codetools.repository.ConnectionConfigRepository;
import com.lzt.operate.codetools.service.ConnectionConfigService;
import com.lzt.operate.entities.ParamData;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lzt
 */
@Service
public class ConnectionConfigServiceImpl implements ConnectionConfigService {

    private final ConnectionConfigRepository repository;

    @Autowired
    public ConnectionConfigServiceImpl(ConnectionConfigRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<ConnectionConfig> page(Example<ConnectionConfig> filter, Pageable pageable) {
        return this.repository.findAll(filter, pageable);
    }

    @Override
    public Optional<ConnectionConfig> get(String id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<ConnectionConfig> findOne(Example<ConnectionConfig> filter) {
        return this.repository.findOne(filter);
    }

    @Override
    public ConnectionConfig save(ConnectionConfig data) {
        beforeSave(data);

        return this.repository.save(data);
    }

    @Override
    public void fixDataBeforeSave(ConnectionConfig entity) {
    }

    public ConnectionConfig fillFromParamJson(ConnectionConfig connectionConfig, ParamData paramJson) {
        var name = paramJson.getByKey(GlobalString.CONNECTION_NAME);
        var dbtype = paramJson.getByKey(GlobalString.CONNECTION_DB_TYPE);
        var host = paramJson.getByKey(GlobalString.CONNECTION_HOST);
        var port = paramJson.getByKey(GlobalString.CONNECTION_PORT);
        var schema = paramJson.getByKey(GlobalString.CONNECTION_SCHEMA);
        var username = paramJson.getByKey(GlobalString.CONNECTION_USERNAME);
        var password = paramJson.getByKey(GlobalString.CONNECTION_PASSWORD);
        var encoding = paramJson.getByKey(GlobalString.CONNECTION_ENCODING);
        var lPort = paramJson.getByKey(GlobalString.CONNECTION_L_PORT);
        var rProt = paramJson.getByKey(GlobalString.CONNECTION_R_PORT);
        var sshPort = paramJson.getByKey(GlobalString.CONNECTION_SSH_PORT);
        var sshHost = paramJson.getByKey(GlobalString.CONNECTION_SSH_HOST);
        var sshUser = paramJson.getByKey(GlobalString.CONNECTION_SSH_USER);
        var sshPassword = paramJson.getByKey(GlobalString.CONNECTION_SSH_PASSWORD);

        connectionConfig.setName(name);
        connectionConfig.setDbType(dbtype);
        connectionConfig.setHost(host);
        connectionConfig.setPort(port);
        connectionConfig.setSchema(schema);
        connectionConfig.setUsername(username);
        connectionConfig.setPassword(password);
        connectionConfig.setEncoding(encoding);
        connectionConfig.setLport(lPort);
        connectionConfig.setRport(rProt);
        connectionConfig.setSshPort(sshPort);
        connectionConfig.setSshHost(sshHost);
        connectionConfig.setSshUser(sshUser);
        connectionConfig.setSshPassword(sshPassword);

        return connectionConfig;
    }

}
