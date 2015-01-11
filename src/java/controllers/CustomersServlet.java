package controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Customer;
import models.DataTableParams;
import util.EMF;
import util.Util;

/**
 *
 * @author Márcio de Souza Júnior
 */
@WebServlet(name = "CustomersServlet", urlPatterns = {
    "/customers",
    "/customers/list",
    "/customers/new",
    "/customers/insert",
    "/customers/edit",
    "/customers/update",
    "/customers/delete",
    "/customers/validate"})
public class CustomersServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.endsWith("customers")) {
            request.getRequestDispatcher("/WEB-INF/views/customers.jsp").forward(request, response);
        } else if (path.endsWith("list")) {
            list(request, response);
        } else if (path.endsWith("new")) {
            _new(request, response);
        } else if (path.endsWith("insert")) {
            insert(request, response);
        } else if (path.endsWith("edit")) {
            edit(request, response);
        } else if (path.endsWith("update")) {
            update(request, response);
        } else if (path.endsWith("delete")) {
            delete(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataTableParams dataTableParams = new DataTableParams(request);
        StringBuilder query = new StringBuilder();
        List<Object> queryParams = new ArrayList<Object>();
        String columns[] = new String[]{"name", "email", "phone"};
        List<Customer> listCustomer = new ArrayList<Customer>();
        EntityManager em = EMF.createEntityManager();
        JsonObject jsonResponse = new JsonObject();

        try {

            query.append("SELECT * FROM CUSTOMER ");
            //queryParams.add("");
            Util.applyFilterOrder(dataTableParams, columns, query, queryParams);
            Query q = em.createNativeQuery(query.toString(), Customer.class);

            // Query params
            int c = 1;
            for (Object o : queryParams) {
                q.setParameter(c, o);
                c++;
            }

            // Set total before pagination
            jsonResponse.addProperty("iTotalDisplayRecords", q.getResultList().size());

            // Pagination
            q.setFirstResult(dataTableParams.getDisplayStart());
            q.setMaxResults(dataTableParams.getDisplayLength());

            listCustomer = q.getResultList();

            /*
             * Total
             */
            queryParams.clear();
            query = new StringBuilder("SELECT * FROM CUSTOMER ");
            //queryParams.add("");

            q = em.createNativeQuery(query.toString(), Customer.class);

            c = 1;
            for (Object o : queryParams) {
                q.setParameter(c, o);
                c++;
            }

            jsonResponse.addProperty("iTotalRecords", q.getResultList().size());
            jsonResponse.addProperty("sEcho", dataTableParams.getEcho());

            JsonArray data = new JsonArray();
            JsonArray row;

            for (Customer customer : listCustomer) {

                row = new JsonArray();

                row.add(new JsonPrimitive(Util.replaceNull(customer.getName())));
                row.add(new JsonPrimitive(Util.replaceNull(customer.getEmail())));
                row.add(new JsonPrimitive(Util.replaceNull(customer.getPhone())));
                row.add(new JsonPrimitive(customer.getId()));

                data.add(row);

            }

            jsonResponse.add("aaData", data);

            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(jsonResponse.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    protected void _new(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
