const latitude = 90;
const longitude = 135;
const url = "https://api.openweathermap.org/data/2.5/weather?lat=90&lon=135&appid=36dbddb8b9ad7bed185fb998d419b35a";

let temperature = document.querySelector(".temp");
let description = document.querySelector(".description");
let feelslike = document.querySelector(".feelslike");

window.addEventListener("load", ()=>{
fetch(url)
    .then((response)=>{
        return response.json();
    })
    .then((data)=>{
        console.log(data);
        temperature.textContent="The current temp is " +
            Math.floor(data.main.temp - 273) + "\u00B0C.";
        feelslike.textContent="It feels like " + 
            Math.floor(data.main.feels_like - 273) + "\u00B0C.";
        description.textContent="If you looked outside, you'd see " +
            data.weather[0].description + ".";
    })
})
