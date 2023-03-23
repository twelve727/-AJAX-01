package edu.kh.community.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/* 필터(Filter)
 * 
 * - 클라이언트 요청 시 생성되는 HttpServletRequest,HttpServletResponse가
 *   요청을 처리하는 Servlet에 도달하기 전
 *   특정 코드를 수행하는 클래스
 *   
 *   [요청 흐름]
 *   
 *   클라이언트 -> 요청 -> HttpServletRequest -> 필터(Filter) -> 요청을 처리
 *		결과 :   		    HttpServletResponse 생성
 *   
 *   - 여러 필터를 만들어 연쇄적으로 연결하여 수행할 수 있다. (FilterChain)
 * */


// ---------------------------------------------------------------- //


//@WebFilter("url패턴")
// 해당 클래스를 필터 클래스로 등록
// url 패턴에 일치하는 요청이 있을 경우, 해당 요청을 필터링한다.

// 1. filterName 속성 : 필터의 이름/순서를 지정
// 2. urlPatterns 속성 : 요청 주소 패턴

// /Community == / == 최상위주소
// * == 모든
// /* == 최상위 주소 하위 모든 == 결국 모든 요청


//---------------------------------------------------------------- //


@WebFilter(filterName="encodingFilter", urlPatterns="/*")
public class EncodingFilter extends HttpFilter implements Filter {

	
//	서버 실행 시 또는 필터 코드 변경 시
//	필터 객체 자동 생성
//	그 때, 필터에 필요한 내용 초기화 하는 메서드
	public void init(FilterConfig fConfig) {
		System.out.println("문자 인코딩 필터 초기화");
	}
	
	
//	서버 실행 중 필터 코드가 변경 되어
//	기존 필터를 없애야 할 때 수행되는 메서드
	public void destroy() {
		System.out.println("문자 인코딩 필터 파괴");
	}
	
	
//	찐 필터 역할 수행메서드
//	import 네이밍 확인
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
//	ServletRequest == HttpServletRequest의 부모 타입
//	ServletResponse == HttpServletResponse의 부모타입
//	--> 필요 시 다운캐스팅
		
//	모든 요청의 문자 인코딩을 UTF-8로 세팅
	request.setCharacterEncoding("UTF-8");	
//	모든 응답의 문자 인코딩 UTF-8로 설정
	response.setCharacterEncoding("UTF-8");		
		
	
	// ---------------------------------------------------------------- //

	// application scope로 최상위 경로를 얻어올 수 있는 값 세팅
	
	// application 내장 객체 얻어오기
	ServletContext application = request.getServletContext();
	
	// 최상위 주소 얻어오기
	// 다운캐스팅
	String contextPath = ((HttpServletRequest)request).getContextPath();
	
	// 세팅
//	header.jsp의 "${contextPath}" 확인
	application.setAttribute("contextPath", contextPath);
	
	//	연결된 다음 필터 수행(없으면 Servlet 수행)
	chain.doFilter(request, response);
	
	
	
	}
	
}