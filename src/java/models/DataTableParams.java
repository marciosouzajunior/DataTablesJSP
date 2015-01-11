/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Márcio de Souza Júnior
 */
public class DataTableParams {

    // Display start point in the current data set.
    int displayStart;

    // Number of records that the table can display in the current draw. It is expected that the number 
    // of records returned will be equal to this number, unless the server has fewer records to return.
    int displayLength;

    // Number of columns being displayed (useful for getting individual column search info)
    int columns;

    // Global search field
    String search;

    // True if the global filter should be treated as a regular expression for advanced filtering, 
    // false if not.
    boolean regex;

    // Indicator for if a column is flagged as searchable or not on the client-side
    boolean searchableCol[];

    // Individual column filter
    String searchCol[];

    // True if the individual column filter should be treated as a regular expression 
    // for advanced filtering, false if not
    boolean regexCol[];

    // Indicator for if a column is flagged as sortable or not on the client-side
    boolean sortableCol[];

    // Number of columns to sort on
    int sortingCols;

    // Column being sorted on (you will need to decode this number for your database)
    int sortCol[];

    // Direction to be sorted - "desc" or "asc".
    String sortDirCol[];

    // The value specified by mDataProp for each column. This can be useful for ensuring 
    // that the processing of data is independent from the order of the columns.
    String dataPropCol[];

    // Information for DataTables to use for rendering.
    String echo;

    public DataTableParams() {
    }

    public DataTableParams(int displayStart, int displayLength, int columns, String search, boolean regex, boolean[] searchableCol, String[] searchCol, boolean[] regexCol, boolean[] sortableCol, int sortingCols, int[] sortCol, String[] sortDirCol, String[] dataPropCol, String echo) {
        this.displayStart = displayStart;
        this.displayLength = displayLength;
        this.columns = columns;
        this.search = search;
        this.regex = regex;
        this.searchableCol = searchableCol;
        this.searchCol = searchCol;
        this.regexCol = regexCol;
        this.sortableCol = sortableCol;
        this.sortingCols = sortingCols;
        this.sortCol = sortCol;
        this.sortDirCol = sortDirCol;
        this.dataPropCol = dataPropCol;
        this.echo = echo;
    }

    public DataTableParams(HttpServletRequest request) {

        this.displayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
        this.displayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
        this.columns = Integer.parseInt(request.getParameter("iColumns"));
        this.search = request.getParameter("sSearch");
        this.regex = Boolean.parseBoolean(request.getParameter("bRegex"));
        this.sortingCols = Integer.parseInt(request.getParameter("iSortingCols"));
        this.echo = request.getParameter("sEcho");

        this.searchableCol = new boolean[columns];
        this.searchCol = new String[columns];
        this.regexCol = new boolean[columns];
        this.sortableCol = new boolean[columns];
        this.dataPropCol = new String[columns];

        for (int c = 0; c < columns; c++) {

            this.searchableCol[c] = Boolean.parseBoolean(request.getParameter("bSearchable_" + c));
            this.searchCol[c] = request.getParameter("sSearch_" + c);
            this.regexCol[c] = Boolean.parseBoolean(request.getParameter("bRegex_" + c));
            this.sortableCol[c] = Boolean.parseBoolean(request.getParameter("bSortable_" + c));
            this.dataPropCol[c] = request.getParameter("mDataProp_" + c);

        }

        this.sortCol = new int[sortingCols];
        this.sortDirCol = new String[sortingCols];

        for (int c = 0; c < sortingCols; c++) {

            this.sortCol[c] = Integer.parseInt(request.getParameter("iSortCol_" + c));
            this.sortDirCol[c] = request.getParameter("sSortDir_" + c);

        }

    }

    public int getDisplayStart() {
        return displayStart;
    }

    public void setDisplayStart(int displayStart) {
        this.displayStart = displayStart;
    }

    public int getDisplayLength() {
        return displayLength;
    }

    public void setDisplayLength(int displayLength) {
        this.displayLength = displayLength;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public boolean isRegex() {
        return regex;
    }

    public void setRegex(boolean regex) {
        this.regex = regex;
    }

    public boolean[] getSearchableCol() {
        return searchableCol;
    }

    public void setSearchableCol(boolean[] searchableCol) {
        this.searchableCol = searchableCol;
    }

    public String[] getSearchCol() {
        return searchCol;
    }

    public void setSearchCol(String[] searchCol) {
        this.searchCol = searchCol;
    }

    public boolean[] getRegexCol() {
        return regexCol;
    }

    public void setRegexCol(boolean[] regexCol) {
        this.regexCol = regexCol;
    }

    public boolean[] getSortableCol() {
        return sortableCol;
    }

    public void setSortableCol(boolean[] sortableCol) {
        this.sortableCol = sortableCol;
    }

    public int getSortingCols() {
        return sortingCols;
    }

    public void setSortingCols(int sortingCols) {
        this.sortingCols = sortingCols;
    }

    public int[] getSortCol() {
        return sortCol;
    }

    public void setSortCol(int[] sortCol) {
        this.sortCol = sortCol;
    }

    public String[] getSortDirCol() {
        return sortDirCol;
    }

    public void setSortDirCol(String[] sortDirCol) {
        this.sortDirCol = sortDirCol;
    }

    public String[] getDataPropCol() {
        return dataPropCol;
    }

    public void setDataPropCol(String[] dataPropCol) {
        this.dataPropCol = dataPropCol;
    }

    public String getEcho() {
        return echo;
    }

    public void setEcho(String echo) {
        this.echo = echo;
    }

}
