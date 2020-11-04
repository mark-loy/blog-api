package com.markloy.markblog.pojo;

public class Visitor {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column visitor.id
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column visitor.account_id
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column visitor.source
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private Integer source;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column visitor.visitor_name
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private String visitorName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column visitor.avatar_url
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private String avatarUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column visitor.state
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private Boolean state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column visitor.gmt_create
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column visitor.gmt_modified
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    private Long gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column visitor.id
     *
     * @return the value of visitor.id
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column visitor.id
     *
     * @param id the value for visitor.id
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column visitor.account_id
     *
     * @return the value of visitor.account_id
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column visitor.account_id
     *
     * @param accountId the value for visitor.account_id
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column visitor.source
     *
     * @return the value of visitor.source
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public Integer getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column visitor.source
     *
     * @param source the value for visitor.source
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column visitor.visitor_name
     *
     * @return the value of visitor.visitor_name
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public String getVisitorName() {
        return visitorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column visitor.visitor_name
     *
     * @param visitorName the value for visitor.visitor_name
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName == null ? null : visitorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column visitor.avatar_url
     *
     * @return the value of visitor.avatar_url
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column visitor.avatar_url
     *
     * @param avatarUrl the value for visitor.avatar_url
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column visitor.state
     *
     * @return the value of visitor.state
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public Boolean getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column visitor.state
     *
     * @param state the value for visitor.state
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column visitor.gmt_create
     *
     * @return the value of visitor.gmt_create
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column visitor.gmt_create
     *
     * @param gmtCreate the value for visitor.gmt_create
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column visitor.gmt_modified
     *
     * @return the value of visitor.gmt_modified
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column visitor.gmt_modified
     *
     * @param gmtModified the value for visitor.gmt_modified
     *
     * @mbg.generated Wed Nov 04 10:44:06 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}