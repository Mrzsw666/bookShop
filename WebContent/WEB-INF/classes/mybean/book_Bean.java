package mybean;
public class book_Bean {
	String []columnName;
	String [][]tableRecord=null;
	int bookNumber;
	String result;
	int id;
	int pageSize=1;
	int totalPages=1;
	int currentPage=1;
	public void setBookNumber(int i){
		bookNumber=i;
	}
	public int getBookNumber(){
		return bookNumber;
	}
	public void setTableRecord(String [][]s){
		tableRecord=s;
	}
	public String[][] getTableRecord(){
		return tableRecord;
	}
	public void setColumnName(String []s){
		columnName=s;
	}
	public String[] getColumnName(){
		return columnName;
	}
	public void setResult(String s){
		result=s;
	}
	public String getResult(){
		return result;
	}
	
	public void setId(int i){
		id=i;
	}
	public int getId(){
		return id;
	}
	public void setPageSize(int size){
		pageSize=size;
	}
	public int getPageSize(){
		return pageSize;
	}
	public void setTotalPages(int n){
		totalPages=n;
	}
	public int getTotalPages(){
		return totalPages;
	}
	public void setCurrentPage(int n){
		currentPage=n;
	}
	public int getCurrentPage(){
		return currentPage;
	}
}

