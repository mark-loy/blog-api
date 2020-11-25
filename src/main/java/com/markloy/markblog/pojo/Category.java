package com.markloy.markblog.pojo;

public class Category {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.id
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.category_name
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    private String categoryName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.article_count
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    private Integer articleCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.gmt_create
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.gmt_modified
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    private Long gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.id
     *
     * @return the value of category.id
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.id
     *
     * @param id the value for category.id
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.category_name
     *
     * @return the value of category.category_name
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.category_name
     *
     * @param categoryName the value for category.category_name
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.article_count
     *
     * @return the value of category.article_count
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    public Integer getArticleCount() {
        return articleCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.article_count
     *
     * @param articleCount the value for category.article_count
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.gmt_create
     *
     * @return the value of category.gmt_create
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.gmt_create
     *
     * @param gmtCreate the value for category.gmt_create
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.gmt_modified
     *
     * @return the value of category.gmt_modified
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.gmt_modified
     *
     * @param gmtModified the value for category.gmt_modified
     *
     * @mbg.generated Wed Nov 25 19:58:20 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}