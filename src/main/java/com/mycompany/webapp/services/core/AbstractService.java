package com.mycompany.webapp.services.core;

import com.mycompany.webapp.services.ErrorMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class AbstractService<T> implements ServiceForCrudOperations<T> {

    private JpaRepository<T, Long> jpaRepository;

    @Override
    public String save(T ob) {
        if (ob == null) {
            return ErrorMessages.OBJECT_IS_NULL;
        }

        String errorMessage = checkObject(ob);

        if (errorMessage == null) {
            jpaRepository.save(ob);
        }

        return errorMessage;
    }

    @Override
    public String update(T ob){
        return save(ob);
    }


    @Override
    public List<T> readAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        T ob = jpaRepository.findOne(id);

        if (ob != null) {
            jpaRepository.delete(ob);
        }
    }

    @Override
    public T read(Long id) {
        return jpaRepository.findOne(id);
    }

    protected void setJpaRepository(JpaRepository<T, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public abstract String checkObject(T ob);
}
