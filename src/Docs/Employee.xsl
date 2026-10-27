<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <style type="text/css">
                    table.tabla {
                        border-collapse: collapse;
                        width: 100%;
                    }
                    th, td {
                        border: 1px solid black;
                        padding: 6px;
                        text-align: center;
                    }
                    th {
                        background-color: #2E9AFE;
                        color: white;
                    }
                    table tr:nth-child(even) {
                        background-color: #e0e0e0;
                    }
                </style>
            </head>
            <body>
                <h1>Employees</h1>
                <table class="tabla">
                    <tr>
                        <th>DNI</th>
                        <th>Ful Name</th>
                        <th>Tlf</th>
                        <th>Active</th>
                    </tr>
                    <xsl:for-each select="employees/employee">
                        <tr>
                            <td><xsl:value-of select="dni"/></td>
                            <td><xsl:value-of select="fullname"/></td>
                            <td><xsl:value-of select="tlf"/></td>
                            <td><xsl:value-of select="active"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
