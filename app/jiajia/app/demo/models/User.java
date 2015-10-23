package jiajia.app.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Index;

import jiajia.common.base.models.BasicGenericModel;

/**
 * 
 * @author lizhong.chen
 * @date 2015年2月26日下午1:43:59
 * @description user
 * @version V1.0
 */
@Entity(name = User.TABLE_NAME)
public class User extends BasicGenericModel {

	public static final String TABLE_NAME = "user";

	@Id
	@GeneratedValue
	private Long uid;

	/**
	 * 用户名
	 */
	@Column(name = "user_name", columnDefinition = " varchar(64) not null", unique = true)
	@Index(name = "idx_user_name")
	private String userName;

	/**
	 * 密码(md5加密)
	 */
	@Column(name = "pwd", columnDefinition = " varchar(32)")
	private String pwd;

	private String email;

	private Boolean isVerification;

	public User() {
	}

	public User(String userName, String pwd, String email, Boolean isVerification) {
		this.userName = userName;
		this.pwd = pwd;
		this.email = email;
		this.isVerification = isVerification;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsVerification() {
		return isVerification;
	}

	public void setIsVerification(Boolean isVerification) {
		this.isVerification = isVerification;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", userName=" + userName + ", pwd=" + pwd + ", email=" + email + ", isVerification="
				+ isVerification + "]";
	}

}
