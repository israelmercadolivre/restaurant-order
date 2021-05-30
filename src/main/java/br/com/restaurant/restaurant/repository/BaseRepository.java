package br.com.restaurant.restaurant.repository;

import br.com.restaurant.restaurant.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseRepository<T extends Order> implements Repository<T> {
    private Class<T> clazz;
    private Map<Long, T> entities = new HashMap<>();
    private Long nextId = 1l;


    public T find(Integer id) {
        return this.entities.get(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(this.entities.values());
    }

    public T save(T entity) {
        Long id = getNextId();
        entity.setId(id);
        return this.entities.put(id, entity);
    }

    public T update(T newEntity) {
        T oldEntity = entities.get(newEntity.getId());
        BeanUtils.copyProperties(newEntity, oldEntity);
        return entities.put(oldEntity.getId(), oldEntity);
    }

    public void delete(Integer id) {
        entities.remove(id);
    }

    private Long getNextId() {
        return nextId++;
    }

    @PostConstruct
    private void loadDataBase() throws IOException {
        File entityFile = getDabaBase();
        if (entityFile.length() != 0) {
            ObjectMapper mapper = new ObjectMapper();
            TypeFactory typeFactory = mapper.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(HashMap.class, Integer.class, clazz);
            this.entities = mapper.readValue(entityFile, mapType);
            this.nextId = (long) this.entities.size();
        }
    }

    @PreDestroy
    private void saveToDataBase() throws IOException {
        File pedidoDataBase = getDabaBase();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(pedidoDataBase, this.entities);
    }

    private File getDabaBase() throws FileNotFoundException {
        String classPath = "classpath:static/order.json";
        return ResourceUtils.getFile(classPath);
    }
}
