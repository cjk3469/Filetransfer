package com.common;

public class UserExceptionTest {
	public void test(String[] a) throws UserException{
		System.out.println("test 호출 성공");
		if(a.length < 1)
			throw new UserException("아무것도 없어요");
		else
			throw new UserException("최종예선", 7000);
		
	}
	
	
	public static void main(String[] args) {
		UserExceptionTest uet = new UserExceptionTest();
		try {
			uet.test(args);
		} catch (UserException e) {
			e.printStackTrace();
		}
	}

}
