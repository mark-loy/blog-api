package com.markloy.markblog.pojo;

public class Tag {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tag.id
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tag.tag_name
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private String tagName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tag.article_count
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private Integer articleCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tag.gmt_create
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tag.gmt_modified
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private Long gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.id
     *
     * @return the value of tag.id
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.id
     *
     * @param id the value for tag.id
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.tag_name
     *
     * @return the value of tag.tag_name
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.tag_name
     *
     * @param tagName the value for tag.tag_name
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.article_count
     *
     * @return the value of tag.article_count
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public Integer getArticleCount() {
        return articleCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.article_count
     *
     * @param articleCount the value for tag.article_count
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.gmt_create
     *
     * @return the value of tag.gmt_create
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.gmt_create
     *
     * @param gmtCreate the value for tag.gmt_create
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tag.gmt_modified
     *
     * @return the value of tag.gmt_modified
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tag.gmt_modified
     *
     * @param gmtModified the value for tag.gmt_modified
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}