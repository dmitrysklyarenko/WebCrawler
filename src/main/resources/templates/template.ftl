<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Generated report</title>
</head>
<body>
    <table border="1px SOLID">
        <tr>
            <th>Site URL</th>
            <th>Country rank</th>
            <th>Global rank</th>
            <th>Alexa's page</th>
        </tr>
        <#list sites as site>
        <tr>
            <td><a href="${site.siteUrl}">${site.siteUrl}</a></td>
            <td>${site.countryRank}</td>
            <td>${site.globalRank}</td>
            <td><a href="${site.alexaLink}">${site.alexaLink}</a></td>
        </tr>
        </#list>
    </table>
</body>
</html>