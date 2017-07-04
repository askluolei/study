package com.luolei.mybatis.model;

public class Pet {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pets.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pets.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pets.gender
     *
     * @mbg.generated
     */
    private Boolean gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pets.birth
     *
     * @mbg.generated
     */
    private String birth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pets.createdAt
     *
     * @mbg.generated
     */
    private Long createdat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pets.updatedAt
     *
     * @mbg.generated
     */
    private Long updatedat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pets.version
     *
     * @mbg.generated
     */
    private Long version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pets.id
     *
     * @return the value of pets.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pets.id
     *
     * @param id the value for pets.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pets.name
     *
     * @return the value of pets.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pets.name
     *
     * @param name the value for pets.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pets.gender
     *
     * @return the value of pets.gender
     *
     * @mbg.generated
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pets.gender
     *
     * @param gender the value for pets.gender
     *
     * @mbg.generated
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pets.birth
     *
     * @return the value of pets.birth
     *
     * @mbg.generated
     */
    public String getBirth() {
        return birth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pets.birth
     *
     * @param birth the value for pets.birth
     *
     * @mbg.generated
     */
    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pets.createdAt
     *
     * @return the value of pets.createdAt
     *
     * @mbg.generated
     */
    public Long getCreatedat() {
        return createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pets.createdAt
     *
     * @param createdat the value for pets.createdAt
     *
     * @mbg.generated
     */
    public void setCreatedat(Long createdat) {
        this.createdat = createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pets.updatedAt
     *
     * @return the value of pets.updatedAt
     *
     * @mbg.generated
     */
    public Long getUpdatedat() {
        return updatedat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pets.updatedAt
     *
     * @param updatedat the value for pets.updatedAt
     *
     * @mbg.generated
     */
    public void setUpdatedat(Long updatedat) {
        this.updatedat = updatedat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pets.version
     *
     * @return the value of pets.version
     *
     * @mbg.generated
     */
    public Long getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pets.version
     *
     * @param version the value for pets.version
     *
     * @mbg.generated
     */
    public void setVersion(Long version) {
        this.version = version;
    }
}