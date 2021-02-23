package com.github.ticoyk.teacherhelperb.services;

import java.util.Set;

// Classes that implements this interface should make use of repository
public interface CrudService<C> {

    C findById(Long id);

    Set<C> getData();

    C createNewObject(C newObject);

    C updateObject(Long id, C object);

    void deleteById(Long id);

}
