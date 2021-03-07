package com.github.ticoyk.teacherhelperb.services;

import java.util.Set;

// Classes that implements this interface should make use of repository
public interface UserCrudService<C> {

    C findById(Long id, String token);

    Set<C> getData(String token);

    C createNewObject(C newObject, String token);

    C updateObject(Long id, C object, String token);

    void deleteById(Long id, String token);

}
