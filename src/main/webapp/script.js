document.addEventListener("DOMContentLoaded",function (){
    var request = new XMLHttpRequest();
    request.open("GET","./GetTime");
    request.send();
    request.onreadystatechange = function(){
        if (request.readyState === 4) {
            var text = JSON.parse(request.responseText);
            document.getElementById("output").innerHTML = text[0].駅+"に"+text[0].時刻+"に到着します。";
        }
    };
});