<%-- 
    Document   : customers
    Created on : Jan 11, 2015, 7:20:48 PM
    Author     : Márcio de Souza Júnior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DataTables Java Example</title>

        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.4/css/jquery.dataTables.css">
        <script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" language="javascript" src="//cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript">

            $(document).ready(function () {
                $.fn.dataTable.ext.legacy.ajax = true;
                $('#example').dataTable({
                    serverSide: true,
                    ajax: '/DataTablesJSP/customers/list'
                });
            });

        </script>
    </head>
    <body>

        <div width="50%">
            <table id="example" class="display" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>E-mail</th>
                        <th>Phone</th>
                    </tr>
                </thead>

                <tfoot>
                    <tr>
                        <th>Nome</th>
                        <th>E-mail</th>
                        <th>Phone</th>
                    </tr>
                </tfoot>
            </table>
        </div>
    </body>
</html>
