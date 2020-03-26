package bsuir.service.impl;

import bsuir.dao.dao.impl.ServiceDao;
import bsuir.dao.entity.Service;
import bsuir.service.CrudService;

import java.util.List;
import java.util.Optional;

public class ServiceService implements CrudService<Service> {
    private ServiceDao serviceDao;

    public ServiceService() {
        this.serviceDao = new ServiceDao();
    }

    @Override
    public void create(Service service) {
        serviceDao.save(service);
    }

    @Override
    public Service get(int id) {
        Optional<Service> optional = serviceDao.get(id);
        Service service = null;
        if (optional.isPresent()) {
            service = optional.get();
        }
        return service;
    }

    @Override
    public List<Service> getAll() {
        return serviceDao.getAll();
    }

    @Override
    public void update(Service service) {
        serviceDao.update(service);
    }

    @Override
    public void delete(int id) {
        Optional<Service> optional = serviceDao.get(id);
        optional.ifPresent(event -> serviceDao.delete(event));
    }
}
