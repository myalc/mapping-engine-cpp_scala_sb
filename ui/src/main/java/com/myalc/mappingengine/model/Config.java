package com.myalc.mappingengine.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Config implements Serializable {

    private String configName;
    private String srcSchema;
    private String dstSchema;
    private String mapping;
    private boolean transform;
    private String dstTopic;
    private boolean result2kafka;
    private boolean validateScr;
    private boolean validateDst;

    public Config() {
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Config)) {
            return false;
        }
        Config config = (Config) o;
        return Objects.equals(configName, config.configName) && Objects.equals(srcSchema, config.srcSchema) && Objects.equals(dstSchema, config.dstSchema) && Objects.equals(mapping, config.mapping) && transform == config.transform && Objects.equals(dstTopic, config.dstTopic) && result2kafka == config.result2kafka && validateScr == config.validateScr && validateDst == config.validateDst;
    }

    @Override
    public int hashCode() {
        return Objects.hash(configName, srcSchema, dstSchema, mapping, transform, dstTopic, result2kafka, validateScr, validateDst);
    }

    @Override
    public String toString() {
        return "{" +
            " configName='" + getConfigName() + "'" +
            ", srcSchema='" + getSrcSchema() + "'" +
            ", dstSchema='" + getDstSchema() + "'" +
            ", mapping='" + getMapping() + "'" +
            ", transform='" + isTransform() + "'" +
            ", dstTopic='" + getDstTopic() + "'" +
            ", result2kafka='" + isResult2kafka() + "'" +
            ", validateScr='" + isValidateScr() + "'" +
            ", validateDst='" + isValidateDst() + "'" +
            "}";
    }

    public String getConfigName() {
        return this.configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getSrcSchema() {
        return this.srcSchema;
    }

    public void setSrcSchema(String srcSchema) {
        this.srcSchema = srcSchema;
    }

    public String getDstSchema() {
        return this.dstSchema;
    }

    public void setDstSchema(String dstSchema) {
        this.dstSchema = dstSchema;
    }

    public String getMapping() {
        return this.mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public boolean isTransform() {
        return this.transform;
    }

    public boolean getTransform() {
        return this.transform;
    }

    public void setTransform(boolean transform) {
        this.transform = transform;
    }

    public String getDstTopic() {
        return this.dstTopic;
    }

    public void setDstTopic(String dstTopic) {
        this.dstTopic = dstTopic;
    }

    public boolean isResult2kafka() {
        return this.result2kafka;
    }

    public boolean getResult2kafka() {
        return this.result2kafka;
    }

    public void setResult2kafka(boolean result2kafka) {
        this.result2kafka = result2kafka;
    }

    public boolean isValidateScr() {
        return this.validateScr;
    }

    public boolean getValidateScr() {
        return this.validateScr;
    }

    public void setValidateScr(boolean validateScr) {
        this.validateScr = validateScr;
    }

    public boolean isValidateDst() {
        return this.validateDst;
    }

    public boolean getValidateDst() {
        return this.validateDst;
    }

    public void setValidateDst(boolean validateDst) {
        this.validateDst = validateDst;
    }

}