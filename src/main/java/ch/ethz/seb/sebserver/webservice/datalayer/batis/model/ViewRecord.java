package ch.ethz.seb.sebserver.webservice.datalayer.batis.model;

import javax.annotation.Generated;

public class ViewRecord {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-11-12T15:37:04.589+01:00", comments="Source field: view.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-11-12T15:37:04.589+01:00", comments="Source field: view.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-11-12T15:37:04.589+01:00", comments="Source field: view.columns")
    private Integer columns;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-11-12T15:37:04.590+01:00", comments="Source field: view.position")
    private Integer position;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-11-12T15:37:04.590+01:00", comments="Source field: view.template_id")
    private Long templateId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-11-12T15:37:04.589+01:00", comments="Source Table: view")
    public ViewRecord(Long id, String name, Integer columns, Integer position, Long templateId) {
        this.id = id;
        this.name = name;
        this.columns = columns;
        this.position = position;
        this.templateId = templateId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-11-12T15:37:04.589+01:00", comments="Source field: view.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-11-12T15:37:04.589+01:00", comments="Source field: view.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-11-12T15:37:04.590+01:00", comments="Source field: view.columns")
    public Integer getColumns() {
        return columns;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-11-12T15:37:04.590+01:00", comments="Source field: view.position")
    public Integer getPosition() {
        return position;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-11-12T15:37:04.590+01:00", comments="Source field: view.template_id")
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbg.generated Tue Nov 12 15:37:04 CET 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", columns=").append(columns);
        sb.append(", position=").append(position);
        sb.append(", templateId=").append(templateId);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbg.generated Tue Nov 12 15:37:04 CET 2019
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ViewRecord other = (ViewRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getColumns() == null ? other.getColumns() == null : this.getColumns().equals(other.getColumns()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view
     *
     * @mbg.generated Tue Nov 12 15:37:04 CET 2019
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getColumns() == null) ? 0 : getColumns().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        return result;
    }
}