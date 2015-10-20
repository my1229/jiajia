/**
 * 
 */
package yunbei.app.demo.models;

/**
 * @author lizhong.chen
 * @date 2015年3月16日下午4:22:41
 * @description TODO
 * @version V1.0
 */

public class Requie {

    private String key;
    private String value;

    public Requie() {
    }

    public Requie(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
