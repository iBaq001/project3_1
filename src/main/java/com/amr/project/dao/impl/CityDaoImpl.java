package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CityDao;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class CityDaoImpl extends ReadWriteDaoImpl<City, Long> implements CityDao {

    @Override
    public City findByCityName(String cityName) {
        TypedQuery<City> query = em.createQuery(
                "select city from City city where city.name = :name", City.class);
        try{
            City city = query.setParameter("name", cityName).getSingleResult();
            return city;
        } catch (Exception e){
            return null;
        }


    }
}
