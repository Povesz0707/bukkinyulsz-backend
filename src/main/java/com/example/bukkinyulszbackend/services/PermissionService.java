package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Permission;
import com.example.bukkinyulszbackend.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionService  extends BaseService implements BaseServiceInterface<Permission>{
    private PermissionRepository permissionRepository;

    @Autowired
    public void setPermissionRepository(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
    @Override
    public Boolean delete(long id) throws BusinessException {
        return null;
    }
    @Override
    public Permission add(Permission data) throws BusinessException {
        Permission newPermission = this.permissionRepository.save(data);
        this.permissionRepository.flush();
        return newPermission;
    }
    @Override
    public List<Permission> list() throws BusinessException {
        return null;
    }

    @Override
    public Permission getById(long id) throws BusinessException {
        return null;
    }

    @Override
    public Permission edit(Permission data) throws BusinessException {
        return null;
    }
}
