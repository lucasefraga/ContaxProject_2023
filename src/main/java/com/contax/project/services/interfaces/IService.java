package com.contax.project.services.interfaces;

import java.util.List;

public interface IService<Entity> {

    List<Entity> findAll();

    Entity findById(Long id);

    Entity save(Entity entity);

    Entity update(Entity entity, Long id);

    Entity delete(Long id);
}
