package com.inbook.main;

public class Execute {

	public static Object service(Service service, Object obj) throws Exception{
		
		return Execute.service(service, obj, 1);
	}

	public static Object service(Service service, Object obj, int log) throws Exception{
		// 시작 시간
		if(log == 1) {
			
			long start = System.currentTimeMillis();
			System.out.println();
			System.out.println("*** [ Execute.service()를 이용해서 서비스 실행 ] ***");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("- 실행되는 클래스 이름 : " + service.getClass().getName());
			System.out.println("- 넘어가는 데이터 : " + obj);
			
		
			Object result = service.service(obj);
			
		
			long end = System.currentTimeMillis();
			
			System.out.println("- 처리 결과 : " + result);
			System.out.println("- 처리 시간 : " + (end - start));
			System.out.println("-----------------------------------------------------------------");
			System.out.println();
			return result;
	
	}
	else {
		
		//ajax 실시간 처리시 파라미터 2개면 콘솔창 안씀
		Object result = service.service(obj);
		
		return result;
	}
}
	
}
