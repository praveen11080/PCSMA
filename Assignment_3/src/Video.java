
public class Video {
	
	private static String file_name;
	private static String file_size;
    private static long duration;
    private static String type;
    private static String url;
    
    
    
    Video(String name, String size, long dur, String type, String url){
    	
    	this.file_name = name;
    	this.file_size = size;
    	this.duration = dur;
    	this.type = type;
    	this.url = url;
    	
    }
    public static String getFile_name() {
		return file_name;
	}


	public static void setFile_name(String file_name) {
		Video.file_name = file_name;
	}


	public static String getFile_size() {
		return file_size;
	}


	public static void setFile_size(String file_size) {
		Video.file_size = file_size;
	}


	public static long getDuration() {
		return duration;
	}


	public static void setDuration(long duration) {
		Video.duration = duration;
	}


	public static String getType() {
		return type;
	}


	public static void setType(String type) {
		Video.type = type;
	}


	public static String getUrl() {
		return url;
	}


	public static void setUrl(String url) {
		Video.url = url;
	}
}
    


