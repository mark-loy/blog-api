package com.markloy.markblog.pojo;

public class Admin {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.id
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.username
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.password
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.email
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.pet_name
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    private String petName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.avatar
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    private String avatar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.state
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    private Integer state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.gmt_create
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.gmt_modified
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    private Long gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.id
     *
     * @return the value of admin.id
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.id
     *
     * @param id the value for admin.id
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.username
     *
     * @return the value of admin.username
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.username
     *
     * @param username the value for admin.username
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.password
     *
     * @return the value of admin.password
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.password
     *
     * @param password the value for admin.password
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.email
     *
     * @return the value of admin.email
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.email
     *
     * @param email the value for admin.email
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.pet_name
     *
     * @return the value of admin.pet_name
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public String getPetName() {
        return petName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.pet_name
     *
     * @param petName the value for admin.pet_name
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public void setPetName(String petName) {
        this.petName = petName == null ? null : petName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.avatar
     *
     * @return the value of admin.avatar
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.avatar
     *
     * @param avatar the value for admin.avatar
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.state
     *
     * @return the value of admin.state
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.state
     *
     * @param state the value for admin.state
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.gmt_create
     *
     * @return the value of admin.gmt_create
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.gmt_create
     *
     * @param gmtCreate the value for admin.gmt_create
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.gmt_modified
     *
     * @return the value of admin.gmt_modified
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.gmt_modified
     *
     * @param gmtModified the value for admin.gmt_modified
     *
     * @mbg.generated Mon Nov 02 11:41:01 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}