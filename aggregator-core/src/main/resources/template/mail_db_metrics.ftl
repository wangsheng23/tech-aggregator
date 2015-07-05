<#-- In FreeMarker, the spaces will be output -->
<html>
<#-- Mail Body -->
<span style="font-weight:bold;">Message:${message}</span>
    <span>metrics value gathered is: <span style="font-weight:bold; color:red">${gatheredValue}</span></span>
    <span>threshold type is: <span style="font-weight:bold; color:red">${thresholdType}</span></span>
    <span>handled value is: <span style="font-weight:bold; color:red">${handledValue}</span></span>
    <span>metrics threshold is: threshold_value = metrics value(user defined) + buffer(buffer percent)</span>
                            <span style="font-weight:bold; color:red">${thresholdValue}</span> = ${metricValue} + ${metricBuffer}
    <span>metrics condition is: <span style="font-weight:bold; color:red">${condition}</span></span>

<span style="font-weight:bold;margin-left:0px">Solution: ${solution}</span>


<#-- Mail signature-->
<#--<#if hasSignature>-->
<#list signatures as signature>
<span style="font-style:italic;margin-left:0px">${signature}</span>
</#list>
<#--</#if>-->
</html>    