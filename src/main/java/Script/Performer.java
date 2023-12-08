package Script;

public class Performer {
	private int Per_id;
	private String Per_name;
	private String image_path_per ;
	
	public int getPer_id() {
		return this.Per_id;
	}
	public void setPer_id(int New_Id) {
		this.Per_id = New_Id;
	}
	
	public String getPer_name() {
		return Per_name;
	}
	public void setPer_name(String per_name) {
		this.Per_name = per_name;
	}
	public String getImg() {
		return this.image_path_per;
	}
	public void setImg(String newImg) {
		this.image_path_per = newImg;
	}
	
}
