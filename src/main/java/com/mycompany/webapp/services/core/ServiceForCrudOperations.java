package com.mycompany.webapp.services.core;

import java.util.List;

public interface ServiceForCrudOperations<T> {

    String save(T ob);

    List<T> readAll();

    String update(T ob);

    T read(Long id);

    void delete(Long id);
}
