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
                <h1>Relations</h1>
                <table class="tabla">
                    <tr>
                        <th>DNI</th>
                        <th>Cod</th>
                        <th>Day</th>
                        <th>Hours</th>
                    </tr>
                    <xsl:for-each select= "relations/relation">
                        <tr>
                            <td><xsl:value-of select="dni"/></td>
                            <td><xsl:value-of select="cod"/></td>
                            <td><xsl:value-of select="day"/></td>
                            <td><xsl:value-of select="hours"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
