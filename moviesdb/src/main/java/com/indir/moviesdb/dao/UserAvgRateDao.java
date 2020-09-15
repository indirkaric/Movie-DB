package com.indir.moviesdb.dao;

import com.indir.moviesdb.dto.UserAvgRateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.*;

@Repository
@Slf4j
public class UserAvgRateDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<UserAvgRateDto> getAverageRateByUser(){
        List<UserAvgRateDto> resultList = new ArrayList<>();
        try {
                String sql = "Select " +
                            "u.id," +
                            "u.first_name," +
                            "u.last_name," +
                            "u.email," +
                            "u.city_id," +
                            "u.username," +
                            "ROUND(AVG(r.rating),2) as average_rate " +
                            "from " +
                            "user as u " +
                            "inner join rating as r on u.id = r.user_id " +
                            "group by r.user_id";

                Query query = entityManager.createNativeQuery(sql);
                List<Object[]> list = query.getResultList();
                for (Object[] ob : list) {
                    UserAvgRateDto user = new UserAvgRateDto();
                    user.setId((Integer)ob[0]);
                    user.setFirstName((String)ob[1]);
                    user.setLastName((String)ob[2]);
                    user.setEmail((String)ob[3]);
                    user.setCityId((Integer)ob[4]);
                    user.setUsername((String)ob[5]);
                    user.setAvgRate((Double)ob[6]);
                    resultList.add(user);
            }

        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }

        return resultList;
    }


}
