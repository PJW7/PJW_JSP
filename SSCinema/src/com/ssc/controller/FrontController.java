package com.ssc.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// 한글 깨짐에 대한 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// getRequestURI() : 현재 프로젝트명과 파일명을 문자열로 반환하는 메서드
		// "/13_ReplyBoard/*.do" 문자열로 반환
		String uri = request.getRequestURI();
		
		// getContextPath() : 현재 프로젝트명을 문자열로 반환하는 메서드
		// "/13_ReplyBoard" 문자열로 반환함
		String path = request.getContextPath();
		
		// command 문자열 변수에는 "*.do"만 반환
		String command = uri.substring(path.length()+1);
		
		Action action = null;
		ActionForward forward = null;
		
		Properties prop = new Properties();		// map의 하위 클래스
		/*
		 * java.util.Properties 클래스
		 * Properties 클래스는 HashTable의 하위 클래스.
		 * 보통은 환경 변수 및 속성 값을 Properties 파일에 저장하여
		 * 쉽게 접근할 수 있는 장점이 있음.
		 * Properties 파일은 일련의 키(key)-값(value)의 한 쌍으로 이루어져 있음
		 * 보통은 파일에 저장하여 사용됨. 파일 이름을  *.properties로 끝나게 함.
		 * InputStream 에 Properties 파일을 인자로 넣어서 그 스트림으로부터
		 * 파일을 가져올 때 많이 사용함. 인자로 들어온 Properties 파일을 읽게 됨.
		 * 읽어들일 때 사용하는 메서드는 load() 메서드를 이용하게 됨.
		 */
		FileInputStream fis = new FileInputStream("E:\\NCS\\workspace(jsp)\\SSCinema\\src\\com\\ssc\\controller\\mapping.properties");
		
		prop.load(fis);
		
		String value = prop.getProperty(command);
		System.out.println("value >>> " + value);
		
		if(value.substring(0, 7).equals("execute")) {
			StringTokenizer st = new StringTokenizer(value,"|");
			String url_1 = st.nextToken(); // "execute"
			String url_2 = st.nextToken(); // "패키지명.클래스명"
			
			/*
			 * 동적 객체 생성 : newInstance()
			 * Class 객체를 이용하면 new 연산자의 사용 없이 동적으로 객체 생성이 가능함.
			 * 코드 작성 시에 클래스 이름을 결정할 수 없고, 런타임 시에 클래스의 이름이 
			 * 결정되는 경우에 유용하게 사용이 됨.
			 * 
			 * newInstance() 메서드는 기본 생성자를 호출해서 객체를 생성하기 때문에
			 * 반드시 클래스에 기본 생성자가 존재하여야 함.
			 * 예외가 발생할 수 있는데 해당 클래스가 추상클래스이거나 인터페이스일 경우에 
			 * 발생하고, 또 하나의 예외는 클래스의 생성자가 접근 제한자로 인해 접근할 수
			 * 없는 경우에 발생을 함. 따라서 예외 처리를 해 주어야 함.
			 * 
			 * 반환타입은 Object 타입이므로 맞게 형변환을 해 주면 되지만, 클래스의 
			 * 타입을 모르는 상태이므로 형변환을 해 줄 수가 없음. 이러한 문제를 해결하기 위해
			 * 인터페이스를 사용을 함.
			 * Class.forName(class이름)은 파라미터로 받은 class 이름에 해당하는
			 * 클래스를 로딩한 후에, 그 클래스에 해당하는 인터페이스를 리턴을 함.
			 * newInstance() 메서드는 로딩한 클래스의 객체를 생성하는 메서드이고,
			 * 이렇게 생성된 객체를 동적 객체 생성이라고 함.
			 */
			
			try {
				Class url = Class.forName(url_2);
				// action = new BbsListAction();
				action = (Action)url.newInstance();	// url에 해당하는 클래스의 객체를 생성한다. 졸라 편하네 ==>동적으로 객체 생성하는 방법
				// 반환타입이 object 타입이라 형변환 해줘야함.
				forward = action.execute(request, response);
				System.out.println("front forward >>> " + forward);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {		// value 값 중에 "execute"가 아닌 경우
			// view page로 이동
			forward = new ActionForward();
			forward.setRedirect(false); 	// *.jsp 페이지로 이동
			forward.setPath(value);
		}
		
		if(forward !=null) {
			if(forward.isRedirect()) { // true인 경우 - *.do
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}
	}
}
