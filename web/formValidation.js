/**
 * Created by sangeshi on 2/9/2015.
 */
function validateForm()
{
    var x = document.forms["myForm"]["testID"].value;
    if(x==null || x=="")
    {
        alert("Enter the test ID");
        return false;
    }
    var tName = document.forms["myForm"]["testName"].value;
    if(tName==null || tName=="")
    {
        alert("Enter the test Name");
        return false;
    }
    var tDes = document.forms["myForm"]["testDescription"].value;
    if(tDes==null || tDes=="")
    {
        alert("Enter the test Description");
        return false;
    }
    var tResult = document.forms["myForm"]["testResult"].value;
    if(tResult==null || tResult=="")
    {
        alert("Enter the test Result");
        return false;
    }
    var exResult = document.forms["myForm"]["expectedResult"].value;
    if(exResult==null || exResult=="")
    {
        alert("Enter the Expected Result");
        return false;
    }
    var tags = document.forms["myForm"]["tags"].value;
    if(tName==null || tName=="")
    {
        alert("Enter the tags");
        return false;
    }
    var time = document.forms["myForm"]["testTimestamp"].value;
    if(time == null || time == "")
    {
        alert("Enter the time stamp");
        return false;
    }
}