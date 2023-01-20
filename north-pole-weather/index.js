const latitude = 90;
const longitude = 135;
const key = "d7feb51c973b7fcdf75a94a692bbacc7";
const url = "https://api.openweathermap.org/data/2.5/weather?lat=90&lon=135&appid=36dbddb8b9ad7bed185fb998d419b35a";

let temperature = document.querySelector(".temp");

window.addEventListener("load", ()=>{
fetch(url)
    .then((response)=>{
        return response.json();
    })
    .then((data)=>{
        console.log(data);
        temperature.textContent="The current temp is " +
            Math.floor(data.main.temp - 273) + "\u00B0C";
        console.log(data.main.temp);
    })
})
