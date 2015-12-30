package object.homesmart.gogo.bean;

public class GatewayBean{

	public String register_id;
	public String register_user;
	public String register_pass;
	
	public String getRegister_id() {
		return register_id;
	}
	
	public void setRegister_id(String register_id) {
		this.register_id = register_id;
	}
	
	public String getRegister_user() {
		return register_user;
	}
	
	public void setRegister_user(String register_user) {
		this.register_user = register_user;
	}
	
	public String getRegister_pass() {
		return register_pass;
	}
	
	public void setRegister_pass(String register_pass) {
		this.register_pass = register_pass;
	}

	public GatewayBean(String register_id, String register_user,
			String register_pass) {
		super();
		this.register_id = register_id;
		this.register_user = register_user;
		this.register_pass = register_pass;
	}

	@Override
	public String toString() {
		return "GatewayBean [register_id=" + register_id + ", register_user="
				+ register_user + ", register_pass=" + register_pass + "]";
	}
}
