package Script;
public class Media {
//attribute
	private int id;	
	private String name;
	private String performer;
	private String img_path;
	private String file_path;
	private String media_song_categories;
	private String year;
	private String type;
	
//	get method
	
	public int getID() {
		return this.id;
	}
	public String getMedia_name() {
		return this.name;
	}
	public String getMedia_file_path() {
		return this.file_path;
	}
	public String getPL_path_img() {
		return this.img_path;
	}
	public String getMedia_type() {
		return this.type;
	}
	public String getMedia_perform() {
		return this.performer;
	}
	public String getMedia_cate() {
		return this.media_song_categories;
	}
	public String getMedia_year() {
		return this.year;
	}
	
//set method	
	public void setMedia_id(int id) {
		this.id = id;
	}
	public void setMedia_name(String name) {
		this.name = name;
	}
	public void setMedia_file_path(String file_path) {
		this.file_path = file_path;
	}
	public void setMedia_img_path(String img_path) {
		this.img_path = img_path;
	}
	public void setMedia_performer(String performer) {
		this.performer = performer;
	}
	public void setMedia_cate(String cate) {
		this.media_song_categories = cate;
	}
	public void setMedia_year(String year) {
		this.year = year;
	}
	public void setMedia_Type(String type) {
		this.type = type;
	}
}
