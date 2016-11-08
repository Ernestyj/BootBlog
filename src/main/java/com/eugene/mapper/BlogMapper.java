package com.eugene.mapper;

import com.eugene.model.Blog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mybatis的mapper
 * 映射所有博客操作的sql语句
 */
@Repository
@Mapper
public interface BlogMapper {
      @Select({
              "select vid,title,tags",
              "from blog_view",
              "limit #{st},10"
      })
      List<Blog> selectTenBlogs(@Param("st") int start) throws Exception;

      @Select("select count(*) from blog_view")
      int selectBlogNum() throws Exception;

      @Select("select distinct name from view_tag")
      @ResultType(String.class)
      List<String> selectTags() throws Exception;

      @Select({"select vid,date,title",
              "from blog_view",
              "order by date desc",
              "limit #{st},12"})
      List<Blog> selectArc(@Param("st") int start) throws Exception;

      @Select({"select title,tags,md",
              "from blog_view",
              "where vid = #{id}",
              "limit 1"
      })
      Blog selectAdmin(@Param("id") int id) throws Exception;

      @Select({"select title,article",
              "from blog_view",
              "where vid = #{id}",
              "limit 1"
      })
      Blog selectView(@Param("id") int id) throws Exception;

      @Select({"select vid,title ",
              "from blog_view",
              "where vid < #{id}",
              "order by vid desc",
              "limit 1"
      })
      Blog selectPreView(@Param("id") int vid) throws Exception;

      @Select({"select vid,title ",
              "from blog_view",
              "where vid > #{id}",
              "limit 1"
      })
      Blog selectNextView(@Param("id") int vid) throws Exception;

      @Select({
              "select distinct vid",
              "from view_tag",
              "where name = #{tag}"
      })
      List<Integer> selectVidBytag(@Param("tag") String tagName) throws Exception;

      @Select({"select date,title",
              "from blog_view",
              "where vid = #{vid}",
              "limit 1"
      })
      Blog selectTagView(@Param("vid") int vid) throws Exception;

      @Insert({"insert into blog_view " ,
              "(date,title,article,tags,md) " ,
              "values(#{bv.date},#{bv.title}," ,
              "#{bv.article},#{bv.tags},#{bv.md})"
      })
      @SelectKey(before=false,keyProperty="bv.vid",resultType=Integer.class,
              statementType= StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
      int insertBlog(@Param("bv") Blog blog) throws Exception;

      @Insert("insert ignore into view_tag (name,vid) values(#{tn},#{id})")
      int insertViewTag(@Param("tn") String tagName, @Param("id") int vid) throws Exception;

      @Delete("delete from view_tag where vid = #{vid}")
      int deleteViewTag(@Param("vid") int vid) throws Exception;

      @Delete("delete from blog_view where vid =#{vid} limit 1")
      int deleteBlogView(@Param("vid") int vid) throws Exception;

      @Update({"update blog_view",
              "set title = #{bv.title},",
              "tags = #{bv.tags},",
              "md = #{bv.md},",
              "article = #{bv.article}",
              "where vid = #{bv.vid}"
      })
      void updateBlogView(@Param("bv") Blog blog) throws Exception;

}
