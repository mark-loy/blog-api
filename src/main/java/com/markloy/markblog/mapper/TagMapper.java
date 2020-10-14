package com.markloy.markblog.mapper;

import com.markloy.markblog.pojo.Category;
import com.markloy.markblog.pojo.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {

    @Select("select id, tag_name from tag where id in (${ids})")
    List<Tag> findByIds(@Param("ids") String ids);

    @Select("select count(*) from tag")
    Integer countTag();

    @Select("select id, tag_name,article_count from tag order by article_count desc limit 10")
    List<Tag> findTop();
}
