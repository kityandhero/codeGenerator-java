package com.lzt.operate.codetools.service.impl;

import com.lzt.operate.codetools.entity.Permission;
import com.lzt.operate.codetools.repository.PermissionRepository;
import com.lzt.operate.codetools.service.PermissionService;
import com.lzt.operate.entities.Competence;
import com.lzt.operate.extensions.StringEx;
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
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Permission> page(Example<Permission> filter, Pageable pageable) {
        return this.repository.findAll(filter, pageable);
    }

    @Override
    public Optional<Permission> get(String id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Permission> findOne(Example<Permission> filter) {
        return this.repository.findOne(filter);
    }

    @Override
    public Permission save(Permission data) {
        beforeSave(data);

        return this.repository.save(data);
    }

    public Competence buildCompetenceEntity(Permission data) {
        var expand = data.getExpand();
        var guidTag = data.getGuidTag();
        var name = data.getName();
        var relativePath = data.getRelativePath();

        var v = new StringEx("");

        if (!StringEx.isNullOrEmpty(expand)) {
            String splitFlag = "|";

            if (expand.contains(splitFlag)) {

                StringEx.split(expand, '|')
                        .stream()
                        .filter(o -> !StringEx.isNullOrEmpty(o))
                        .forEach(item -> v.append("0"));

            } else {
                v.append("0");
            }
        }

        var result = new Competence();

        result.setGuidTag(new StringEx(guidTag));
        result.setName(new StringEx(name));
        result.setRelativePath(new StringEx(relativePath));
        result.setExplain(new StringEx(expand));
        result.setExpansionSet(v);

        return result;
    }

    @Override
    public void fixDataBeforeSave(Permission entity) {

    }
}