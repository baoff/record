package com.foot.record.base;
import javax.servlet.ServletContext;
import org.springframework.web.context.ContextLoader;
import com.foot.record.exception.PathException;

public class BaseController {
	private static final long serialVersionUID = 0L;
	
	public Object getBean(String name){
		return ContextLoader.getCurrentWebApplicationContext().getBean(name);
	}
	
	public ServletContext getContext(){
		return ContextLoader.getCurrentWebApplicationContext().getServletContext();
	}
	
	public String getPhysicalPath(String path) throws PathException {
		if( !path.startsWith("/")){
			System.err.println("Path format is not valid!");
			throw new PathException();
		}else{
			StringBuffer physicalPath = new StringBuffer(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(path).replace("\\", "/"));
			return physicalPath.toString();
		}
	}
	
	public String getServerPhysicalPath() throws PathException{
		return getPhysicalPath("/");
	}

}