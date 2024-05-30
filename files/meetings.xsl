<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <body>
                <h2>Meetings Schedule</h2>
                <table border="1">
                    <tr>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Person</th>
                        <th>Place</th>
                    </tr>
                    <xsl:for-each select="Meetings/Meeting">
                        <tr>
                            <td><xsl:value-of select="@date"/></td>
                            <td><xsl:value-of select="Time"/></td>
                            <td><xsl:value-of select="Person"/></td>
                            <td><xsl:value-of select="Place"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
