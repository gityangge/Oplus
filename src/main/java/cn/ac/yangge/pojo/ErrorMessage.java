package cn.ac.yangge.pojo;

public class ErrorMessage {
	private int id;

	private String err;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = ""+err;
	}
	@Override
	 public String toString() {
        return String.format("ERROR:%s", err);
    }
}
