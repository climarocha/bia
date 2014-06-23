package br.com.ia.model;

import java.io.Serializable;

public class TemplateTable implements Serializable {
	
	private static final long serialVersionUID = 452300198991351089L;
	
	public TemplateTable() {}

	/// Request sequence number sent by DataTable,
    /// same value must be returned in response
    private String sEcho;
    
	private long iTotalRecords;
    private long iTotalDisplayRecords;

    /// Text used for filtering
    private String sSearch;

    /// Number of records that should be shown in table
    private int iDisplayLength;

    /// First record that should be shown(used for paging)
    private int iDisplayStart;

    /// Number of columns in table
    private int iColumns;

    /// Number of columns that are used in sorting
    private int iSortingCols;
    
    private String nomeAux;

	/// First sort column numeric index, possible to have 
    /// _1,_2 etc for multi column sorting
    private int iSortCol_0;
    
    /// Sort direction of the first sorted column, asc or desc
    private String sSortDir_0;

    /// Comma separated list of column names
    private String sColumns;

	/*private List<ITableData> aaData;
    
	public List<ITableData> getAaData() {
		return aaData;
	}

	public void setAaData(List<ITableData> aaData) {
		this.aaData = aaData;
	}*/

	public TemplateTable(String sEcho, int iColumns,
			int iDisplayLength, int iSortingCols, String sSortDir_0) {
		this.sEcho=sEcho;
		this.iColumns=iColumns; 
		this.iDisplayLength=iDisplayLength;
		this.iSortingCols=iSortingCols;
		this.sSortDir_0=sSortDir_0;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsSearch() {
		return sSearch;
	}
	
	public String getNomeAux() {
		return nomeAux;
	}

	public void setNomeAux(String nomeAux) {
		this.nomeAux = nomeAux;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public int getiSortCol_0() {
		return iSortCol_0;
	}

	public void setiSortCol_0(int iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}

	public String getsSortDir_0() {
		return sSortDir_0;
	}

	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

}
