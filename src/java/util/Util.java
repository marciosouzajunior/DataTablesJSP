package util;

import java.util.List;
import models.DataTableParams;

/**
 *
 * @author Márcio de Souza Júnior
 */
public class Util {

    public static void applyFilterOrder(DataTableParams dataTableParams, String colunas[], StringBuilder query, List<Object> queryParams) {

        // Filter
        if (isNotEmpty(dataTableParams.getSearch())) {

            query.append("WHERE (");

            for (int c = 0; c < colunas.length; c++) {

                if (isNotEmpty(dataTableParams.getSearchableCol()[c])
                        && dataTableParams.getSearchableCol()[c] == true) {

                    query.append(colunas[c]).append(" LIKE ? OR ");
                    queryParams.add("%" + dataTableParams.getSearch() + "%");

                }

            }

            query.delete(query.length() - 4, query.length());
            query.append(") ");

        }

        // Individual filter
        for (int c = 0; c < colunas.length; c++) {

            if (isNotEmpty(dataTableParams.getSearchableCol()[c])
                    && dataTableParams.getSearchableCol()[c] == true
                    && isNotEmpty(dataTableParams.getSearchCol()[c])) {

                if (query.indexOf("WHERE") == -1) {
                    query.append("WHERE ");
                } else {
                    query.append(" AND ");
                }

                query.append(colunas[c]).append(" LIKE ? ");
                queryParams.add("%" + dataTableParams.getSearchCol()[c] + "%");

            }

        }

        // Order
        if (isNotEmpty(dataTableParams.getSortCol())) {

            query.append("ORDER BY  ");

            for (int c = 0; c < dataTableParams.getSortingCols(); c++) {

                if (dataTableParams.getSortableCol()[dataTableParams.getSortCol()[c]] == true) {
                    query.append(colunas[dataTableParams.getSortCol()[c]])
                            .append(" ").append(dataTableParams.getSortDirCol()[c]).append(", ");
                }

            }

            query.delete(query.length() - 2, query.length());
            if (query.substring(query.length() - 8).equals("ORDER BY")) {
                query.delete(query.length() - 8, query.length());
            }

        }

    }

    public static boolean isNotEmpty(Object o) {

        if (o == null) {
            return false;
        }

        if (o instanceof String[]) {
            String[] str = (String[]) o;
            for (String s : str) {
                if (s == null
                        || s.equalsIgnoreCase("")) {
                    return false;
                }
            }
        }

        if (o instanceof String) {
            String str = (String) o;
            if (str.equalsIgnoreCase("")) {
                return false;
            }
        }

        return true;

    }

    public static String replaceNull(String input) {
        return input == null ? "" : input;
    }

}
