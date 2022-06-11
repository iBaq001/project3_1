package com.amr.project.util;

import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SingleResultUtil<T> {
   private static final Logger LOGGER = Logger.getLogger("SingleResultUtilLogger");

    public static <T> T getSingleResultOrNull(TypedQuery<T> query) {
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Результатов запроса не найдено", e);
            return null;
        } catch (NonUniqueResultException ex) {
            LOGGER.log(Level.WARNING, " Найдено более одного результата запроса", ex);
            return null;
        }
    }
}
