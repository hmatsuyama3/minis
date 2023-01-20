const latitude = 90;
const longitude = 135;
const key = "d7feb51c973b7fcdf75a94a692bbacc7";
const url = "https://api.openweathermap.org/data/3.0/onecall?lat=90&lon=135&exclude={part}&appid=d7feb51c973b7fcdf75a94a692bbacc7";

let temperature = document.querySelector(".temp");

window.addEventListener("load", ()=>{
fetch(url)
    .then((response)=>{
        return response.json();
    })
    .then((data)=>{
        console.log(data);
        temperature.textContent=
            Math.floor(data.main.temp - 273) + "C";
        console.log(data.main.temp);
    })
})
