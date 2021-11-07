package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


// class utilisé si l'utilisateur n'est pas authentifié et il demande une page comme l'index ou l'ajout d'une commande 
public class UserInterceptor extends AbstractInterceptor {
	 private static final long serialVersionUID = 1L;

	 @Override
	 public String intercept(ActionInvocation invocation)throws Exception{
	       HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
	       HttpSession session = request.getSession(); // get session
	       if(session.getAttribute("username") == null) { // si l'utilisateur n'existe pas dans la session
	    	   return "failure"; // diriger vers page login
	       }
	       else // sinon
	    	   return invocation.invoke(); // continuer de diriger vers page demandé
	    }
}
