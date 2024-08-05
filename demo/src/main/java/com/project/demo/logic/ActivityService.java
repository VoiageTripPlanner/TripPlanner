package com.project.demo.logic;

import com.project.demo.entity.Activity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService implements IService<Activity, Integer> {
    @Override
    public Activity save(Activity entity) {
        return null;
    }

    @Override
    public List<Activity> findAll() {
        return null;
    }

    @Override
    public Activity findById(Integer integer) {
        return null;
    }

    @Override
    public Activity update(Activity entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    public void saveActivities(List<Activity> activities) {
    }
}
