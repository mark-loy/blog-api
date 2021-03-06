package com.markloy.markblog.mapper;

import com.markloy.markblog.pojo.VisitorLike;
import com.markloy.markblog.pojo.VisitorLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface VisitorLikeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    long countByExample(VisitorLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    int deleteByExample(VisitorLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    int insert(VisitorLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    int insertSelective(VisitorLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    List<VisitorLike> selectByExampleWithRowbounds(VisitorLikeExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    List<VisitorLike> selectByExample(VisitorLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    VisitorLike selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    int updateByExampleSelective(@Param("record") VisitorLike record, @Param("example") VisitorLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    int updateByExample(@Param("record") VisitorLike record, @Param("example") VisitorLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    int updateByPrimaryKeySelective(VisitorLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table visitorlike
     *
     * @mbg.generated Sat Nov 28 18:39:34 CST 2020
     */
    int updateByPrimaryKey(VisitorLike record);
}