package o2o.openplatform.sdk.dto;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import net.sf.json.JSONObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * <p>Title: ReturnJson</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-05-02 18:27
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "msg",
        "data",
        "success"
})
public class ReturnJson {

    @JsonProperty("code")
    private String code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private String data;
    @JsonProperty("success")
    private Boolean success;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The code
     */
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     *     The code
     */
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     *     The msg
     */
    @JsonProperty("msg")
    public String getMsg() {
        return msg;
    }

    /**
     *
     * @param msg
     *     The msg
     */
    @JsonProperty("msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     *
     * @return
     *     The data
     */
    @JsonProperty("data")
    public String getData() {
        return data;
    }

    /**
     *
     * @param data
     *     The data
     */
    @JsonProperty("data")
    public void setData(String data) {
        this.data = data==null?null:data.replaceAll("\\\\","").replaceAll("\"\\{","{").replaceAll("\\}\"","\\}");
    }

    /**
     *
     * @return
     *     The success
     */
    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     *     The success
     */
    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(code).append(msg).append(data).append(success).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ReturnJson) == false) {
            return false;
        }
        ReturnJson rhs = ((ReturnJson) other);
        return new EqualsBuilder().append(code, rhs.code).append(msg, rhs.msg).append(data, rhs.data).append(success, rhs.success).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
