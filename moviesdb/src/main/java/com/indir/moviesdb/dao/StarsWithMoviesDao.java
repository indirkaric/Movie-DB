package com.indir.moviesdb.dao;

import com.indir.moviesdb.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.*;
import static java.util.stream.Collectors.groupingBy;

@Repository
@Slf4j
public class StarsWithMoviesDao {

    @PersistenceContext
    EntityManager entityManager;

    public Map<String,List<StarsWithMoviesDto>> getStarsWithMovies(){
        List<StarsWithMoviesDto> resultList = new ArrayList<>();
        Map<String, List<StarsWithMoviesDto>> groupedResults;
        try {
            String sql = "Select " +
                         "mv.title," +
                         "mv.year," +
                         "mv.rating," +
                         "CONCAT(st.first_name,' ',st.last_name) as full_name " +
                         "from movie as mv " +
                         "inner join movie_star as ms on ms.movie_id = mv.id " +
                         "inner join star as st on st.id = ms.star_id";

            Query query = entityManager.createNativeQuery(sql);
            List<Object[]> list = query.getResultList();

            for (Object[] ob : list) {
                StarsWithMoviesDto dto = new StarsWithMoviesDto();
                dto.setTitle((String) ob[0]);
                dto.setYear((int) ob[1]);
                dto.setRating((Float) ob[2]);
                dto.setName((String) ob[3]);
                resultList.add(dto);
            }
            groupedResults = resultList.stream().collect(groupingBy(StarsWithMoviesDto::getName));

        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return groupedResults;
    }

    public StarWithTheMostMoviesDto getStarWithTheMostMovies(){
        StarWithTheMostMoviesDto starWithTheMostMoviesDto = new StarWithTheMostMoviesDto();
        try {
                String sql =    "Select " +
                            "CONCAT(s.first_name,' ',s.last_name) as full_name," +
                            "COUNT(*) as total_movies " +
                            "from " +
                            "star as s " +
                            "inner join movie_star as ms on  s.id = ms.star_id " +
                            "inner join  movie as m on m.id = ms.movie_id " +
                            "group by s.id " +
                            "order by total_movies DESC " +
                            "limit 1";

                Query query = entityManager.createNativeQuery(sql);
                List<Object[]> list = query.getResultList();
                for (Object[] ob : list) {
                    starWithTheMostMoviesDto.setFullName((String)ob[0]);
                    starWithTheMostMoviesDto.setTotalNumber(((BigInteger) ob[1]).intValue());

            }

        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return starWithTheMostMoviesDto;
    }
}
