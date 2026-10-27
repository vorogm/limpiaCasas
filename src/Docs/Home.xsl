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
                <h1>Homes</h1>

                <table class="tabla">
                    <tr>
                        <th>Cod</th>
                        <th>Type</th>
                        <th>City</th>
                        <th>Surface</th>
                        <th>Rooms</th>                        
                        <th>Price</th>
                        <th>Pictures</th>
                    </tr>
                    <xsl:for-each select="homes/home">
                        <tr>
                            <td><xsl:value-of select="cod"/></td>
                            <td><xsl:value-of select="type"/></td>
                            <td><xsl:value-of select="city"/></td>
                            <td>
                                <xsl:value-of select="surface"/>
                                <xsl:text> </xsl:text>
                                <xsl:value-of select="surface/@unit"/>
                            </td>
                            <td><xsl:value-of select="rooms"/></td>                            
                            <td>
                                <xsl:value-of select="price"/>
                                <xsl:text> </xsl:text>
                                <xsl:value-of select="price/@coin"/>
                            </td>
                            <td>
                                <xsl:for-each select="pictures">
                                    <xsl:value-of select="."/>
                                    <br/>
                                </xsl:for-each>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

