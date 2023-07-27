function register1()
{
    var n1=document.getElementById("F1").value
    var n2=document.getElementById("L1").value
    var n3=document.getElementsByName("gender")

    for(i=0;i<n3.length;i++){
        if(n3[i].checked){
            var gen=n3[i].value

        }

    }

    var n4=document.getElementById("M1").value
    var n5=document.getElementById("E1").value

    document.getElementById("t1").innerHTML=n1
    document.getElementById("t2").innerHTML=n2
    document.getElementById("t3").innerHTML=gen
    document.getElementById("t4").innerHTML=n4
    document.getElementById("t5").innerHTML=n5



}
