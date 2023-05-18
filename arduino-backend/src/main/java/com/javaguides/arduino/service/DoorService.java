package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.DoorBean;
import com.javaguides.arduino.dao.DoorDAO;
import com.javaguides.arduino.entity.Door;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoorService {

    private final DoorDAO doorDAO;

    public DoorService(DoorDAO doorDAO) {
        this.doorDAO = doorDAO;
    }

    public void save(DoorBean doorBean) {
        Door saveResult = doorDAO.saveAndFlush(convertBeanToEntity(doorBean));
        doorDAO.save(saveResult);
    }

    public List<DoorBean> searchAll() {
        return doorDAO.findAll()
                .stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public Optional<DoorBean> getById(Integer id) {
        Optional<Door> optional = doorDAO.findById(id);
        if (optional.isPresent()) {
            Door entity = optional.get();
            DoorBean bean = convertEntityToBean(entity);
            return Optional.of(bean);
        } else {
            return Optional.empty();
        }
    }

    public void update(DoorBean doorBean) {
        Optional<Door> optional = doorDAO.findById(doorBean.getId());
        Door door = optional.get();
        door.setName(doorBean.getName());
        door.setLocation(doorBean.getLocation());
        if (doorBean.getDescription() != null) {
            door.setDescription(doorBean.getDescription());
        }
        doorDAO.update(door);
    }

    public void delete(Integer id) {
        doorDAO.deleteById(id);
    }

    private DoorBean convertEntityToBean(Door door) {
        DoorBean doorBean = new DoorBean();
        doorBean.setId(door.getId());
        doorBean.setName(door.getName());
        doorBean.setLocation(door.getLocation());
        doorBean.setDescription(door.getDescription());
        return doorBean;
    }

    private Door convertBeanToEntity(DoorBean doorBean) {
        Door door = new Door();
        door.setId(doorBean.getId());
        door.setName(doorBean.getName());
        door.setLocation(doorBean.getLocation());
        door.setDescription(doorBean.getDescription());
        return door;
    }
}
