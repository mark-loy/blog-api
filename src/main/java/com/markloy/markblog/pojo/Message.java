package com.markloy.markblog.pojo;

public class Message {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.id
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.type
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.parent_id
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    private Integer parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.visitor_id
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    private Integer visitorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.content
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.gmt_create
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    private Long gmtCreate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.id
     *
     * @return the value of message.id
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.id
     *
     * @param id the value for message.id
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.type
     *
     * @return the value of message.type
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.type
     *
     * @param type the value for message.type
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.parent_id
     *
     * @return the value of message.parent_id
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.parent_id
     *
     * @param parentId the value for message.parent_id
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.visitor_id
     *
     * @return the value of message.visitor_id
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public Integer getVisitorId() {
        return visitorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.visitor_id
     *
     * @param visitorId the value for message.visitor_id
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.content
     *
     * @return the value of message.content
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.content
     *
     * @param content the value for message.content
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.gmt_create
     *
     * @return the value of message.gmt_create
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.gmt_create
     *
     * @param gmtCreate the value for message.gmt_create
     *
     * @mbg.generated Mon Nov 09 19:31:53 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}