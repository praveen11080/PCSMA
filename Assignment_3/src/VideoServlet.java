import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.runtime.Log;

/**
 * Servlet implementation class VideoData
 */
public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Video> video_list = new ArrayList<Video>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VideoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method
		response.setContentType("text/plain");
int i=0;
		for(i=0;i<video_list.size();i++){
			response.getWriter().write(
					video_list.get(i).getFile_name() + " , " + video_list.get(i).getUrl() + " , "
							+ video_list.get(i).getFile_size() + " , " + video_list.get(i).getDuration() + " ,"
							+ video_list.get(i).getType() + "\n");
		}
		
		

		// response.getWriter().write("hererererer");

		// String file_name = request.getParameter("file_name");
		/*
		 * System.out.print("FileName  "+file_name); if(file_name.isEmpty()){
		 * System.out.print("hereIam"+file_name); for(Video v :
		 * this.video_list){
		 * response.getWriter().write(v.getFile_name()+"\n"+v.getUrl());} }
		 * 
		 * 
		 * else{ int count=0; System.out.print("FileName  "+file_name);
		 * for(Video v : this.video_list){ count++;
		 * if(v.getFile_name().equals(file_name)){
		 * 
		 * response.getWriter().write(v.getFile_name()+"\n"+v.getUrl()); break;
		 * } else if(count==video_list.size()){
		 * response.getWriter().write("Video not available on server"); }
		 * 
		 * }
		 * 
		 * 
		 * }
		 */
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().write("\nDeleted");
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
 response.setContentType("text/plain");
		 
		 
		 String file_name = request.getParameter("file_name");
		 String file_size = request.getParameter("file_size");
		 String duration_str = request.getParameter("duration");
		 String type = request.getParameter("type");
		 String url = request.getParameter("url");
		 
		 long duration = -1;
		 
		 try{
			 duration = Long.parseLong(duration_str);
		 }catch(NumberFormatException e){
			 
		 }
		 
		 
		
		 if(file_name == null || file_size == null || duration_str == null||
				 file_name.trim().length() < 1 ||file_size.trim().length() < 1 
				 || duration_str.trim().length() < 1 || duration <= 0
				 )
		 {
			 
			 response.sendError(400);
			 response.getWriter().write("\nWrong input");
		 }else{
			 int flag=1;
			 int i=0;
				for(i=0;i<video_list.size();i++){
					if(video_list.get(i).equals(file_name)){
						video_list.remove(i);	
						flag=0;
						video_list.add(new Video(file_name,file_size,duration,type ,url));
						 response.getWriter().write("\nvideo updated");
					}
				}
			 
		
			 
			 if(flag==1){
				 video_list.add(new Video(file_name,file_size,duration,type ,url));
				 response.getWriter().write("\nvideo added");
			 }
			 
			 
			 
		 	
		 }
		 
		 
		 //response.getWriter().write("hererererer");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");

		String file_name = request.getParameter("file_name");
		String file_size = request.getParameter("file_size");
		String duration_str = request.getParameter("duration");
		String type = request.getParameter("type");
		String url = request.getParameter("url");

		long duration = -1;

		try {
			duration = Long.parseLong(duration_str);
		} catch (NumberFormatException e) {

		}

		if (file_name == null || file_size == null || duration_str == null
				|| file_name.trim().length() < 1
				|| file_size.trim().length() < 1
				|| duration_str.trim().length() < 1 || duration <= 0) {

			response.sendError(400);
			response.getWriter().write("\nWrong input");
		} else {
			video_list
					.add(new Video(file_name, file_size, duration, type, url));
			response.getWriter().write("\nvideo data added");

		}

		// response.getWriter().write("hererererer");
	}

}