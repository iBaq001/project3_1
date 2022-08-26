package com.amr.project.dao.impl;


import com.amr.project.dao.abstracts.CountryDao;
import com.amr.project.model.entity.Country;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public class CountryDaoImpl extends ReadWriteDaoImpl<Country, Long> implements CountryDao {
}
