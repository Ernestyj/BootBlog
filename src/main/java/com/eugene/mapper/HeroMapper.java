package com.eugene.mapper;

import com.eugene.model.Hero;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DCLab on 2016/11/10.
 */
@Repository
@Mapper
public interface HeroMapper {

    @Select({"select id,name",
            "from hero"
    })
    List<Hero> selectAll() throws Exception;

    @Update({"update hero set",
            "name = #{name} ",
            "where id = #{id}"
    })
    int updateName(@Param("id") int id, @Param("name") String newName) throws Exception;

    @Insert({"insert into hero",
            "set name= #{hero.name},",
            "id= #{hero.id}"
    })
    int insertHero(@Param("hero") Hero hero) throws Exception;

    @Delete({"delete from hero",
            "where id = #{id}",
            "limit 1"
    })
    int deleteHero(@Param("id") int id) throws Exception;

}
