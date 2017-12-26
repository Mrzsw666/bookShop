package mybean;

public class User_Bean {
	String UserName;
	String Password;
	String Address;
	String Email;
	int Phone;
	String []Book;
	int isSuper;
	String Result;
	public void setUserName(String s){
		UserName=s;
	}
	public String getUserName(){
		return UserName;
	}
	public void setEmail(String s){
		Email=s;
	}
	public String getEmail(){
		return Email;
	}
	public void setPassword(String s){
		Password=s;
	}
	public String getPassword(){
		return Password;
	}
	public void setAddress(String s){
		Address=s;
	}
	public String getAddress(){
		return Address;
	}
	
	public void setPhone(int i){
		Phone=i;
	}
	public int getPhone(){
		return Phone;
	}
	public void setBook(String[] s){
		Book=s;
	}
	public String[] getBook(){
		return Book;
	}
	public void setisSuper(int n){
		isSuper=n;
	}
	public int getisSuper(){
		return isSuper;
	}
	public void setResult(String s){
		Result=s;
	}
	public String getResult(){
		return Result;
	}
}
