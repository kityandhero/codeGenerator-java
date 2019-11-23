package com.lzt.operate.codetools.service.impl;

import com.lzt.operate.codetools.entity.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.codetools.service.OperatorService;
import com.lzt.operate.extensions.StringEx;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author lzt
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    private final OperatorRepository repository;

    @Autowired
    public OperatorServiceImpl(OperatorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Operator> page(Example<Operator> filter, Pageable pageable) {
        return this.repository.findAll(filter, pageable);
    }

    @Override
    public Optional<Operator> get(String id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Operator> findOne(Example<Operator> filter) {
        return this.repository.findOne(filter);
    }

    @Override
    public Operator save(Operator entity) {
        beforeSave(entity);

        return this.repository.save(entity);
    }

    @Override
    public void fixDataBeforeSave(Operator entity) {
        var salt = entity.getSlat();

        salt = StringEx.isNullOrEmpty(salt) ? UUID.randomUUID()
                                                  .toString()
                                                  .replaceAll("-", "")
                                                  .toLowerCase() : salt;

        entity.setSlat(salt);
    }
}
