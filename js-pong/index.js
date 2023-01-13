const gameBoard = document.querySelector("#gameBoard");
const ctx = gameBoard.getContext("2d"); //what you draw on
const scoreText = document.querySelector("#scoreText");
const resetBtn = document.querySelector("#resetBtn");
const gameWidth = gameBoard.width;  //storing these in a variable makes them easily accessible
const gameHeight = gameBoard.height;
const boardBackground = "forestgreen";
const paddle1Color = "lightblue";
const paddle2Color = "red";
const paddleBorder = "black";
const ballColor = "yellow";
const ballBorderColor = "black";
const ballRadius = 12.5;
const paddleSpeed = 50;
let intervalID;
let ballSpeed = 1;
let ballX = gameWidth/2; //initial ball location is at the center of the board.
let ballY = gameHeight/2;
let ballXDirection = 0; //which direction is the ball headed on the x axis?
let ballYDirection = 0;
let player1Score = 0;
let player2Score = 0;
let paddle1 = {
    width: 25, 
    height: 100,
    x: 0, //starting x and y coordinates
    y: 0  //it will be at the top left corner of the board.
}
let paddle2 = {
    width: 25, 
    height: 100,
    x: gameWidth-25,  //start it at the bottom right corner of teh board.
    y: gameHeight-100  //must account for margins :)
}
window.addEventListener("keydown", changeDirection);  
resetBtn.addEventListener("click", resetGame);

gameStart();

function gameStart(){
    createBall();
    nextTick();

};
function nextTick(){
    intervalID = setTimeout(() =>{ //what are all the things we're going to do?
        clearBoard();
        drawPaddles();
        moveBall();
        drawBall(ballX, ballY);
        checkCollision();
        nextTick();
    }, 10); //repeat routine every 10ms
};
function clearBoard(){
    ctx.fillStyle=boardBackground;
    ctx.fillRect(0,0,gameWidth,gameHeight); //begin in top left corner, then add background ht & wd
};
function drawPaddles(){
    ctx.strokeStyle = paddleBorder;

    ctx.fillStyle = paddle1Color;
    ctx.fillRect(paddle1.x, paddle1.y, paddle1.width, paddle1.height); //fills paddle with color 
    ctx.strokeRect(paddle1.x, paddle1.y, paddle1.width, paddle1.height); //adds border

    ctx.fillStyle = paddle2Color;
    ctx.fillRect(paddle2.x, paddle2.y, paddle2.width, paddle2.height);
    ctx.strokeRect(paddle2.x, paddle2.y, paddle2.width, paddle2.height);
};
function createBall(){
    ballSpeed=1;
    if(Math.round(Math.random())==1){ //get a random number that is 0 or 1.
        ballXDirection=1;
    }else{
        ballXDirection=-1;
    }
        if(Math.round(Math.random())==1){
        ballYDirection=1;
    }else{
        ballYDirection=-1;
    }

    ballX = gameWidth/2;
    ballY = gameHeight/2;
    drawBall(ballX, ballY);
};
function moveBall(){
    ballX +=(ballSpeed*ballXDirection);
    ballY +=(ballSpeed*ballYDirection);
};
function drawBall(ballX, ballY){
    ctx.fillStyle = ballColor;
    ctx.strokeStyle=ballBorderColor;
    ctx.lineWidth = 2;
    ctx.beginPath();
    ctx.arc(ballX, ballY, ballRadius, 0, 2*Math.PI);
    ctx.stroke();
    ctx.fill();
};
function checkCollision(){
    if(ballY<=0 + ballRadius){ //check for top border. if it hits, then bounce off. add ball radius to account for the size of the ball. 
        ballYDirection*=-1;
    }
    if(ballY>=gameHeight-ballRadius){ //check for bottom border.
        ballYDirection*=-1;
    }
    //conditions to check for left & right borders. if it touched, update the score & start a new play.
    if(ballX<=0){ 
        player2Score+=1;
        updateScore();
        createBall();
        return;
    }
    if(ballX>=gameWidth){
        console.log("ded");
        player1Score+=1;
        updateScore();
        createBall();
        return;
    }
    //bounce off the paddles...
    if(ballX<=(paddle1.x + paddle1.width + ballRadius)){ 
        if(ballY>paddle1.y && ballY<paddle1.y + paddle1.height){
            ballX=(paddle1.x+paddle1.width)+ballRadius; //if ball gets stuck corner - prevents it from getting stuck in the paddle.
            ballXDirection*=-1;
            ballSpeed+=1; //increase speed for fun
        }
    }
    if(ballX>=(paddle2.x - ballRadius)){
   
        if(ballY>paddle2.y && ballY<paddle2.y + paddle2.height){
            console.log("bounce");
            ballX=(paddle2.x-ballRadius); //if ball gets stuck
            ballXDirection*=-1;
            ballSpeed+=1;
        }
    }
};
function changeDirection(event){
    const keyPressed = event.keyCode;
    const paddle1Up = 87; //W
    const paddle1Down = 83; //S
    const paddle2Up = 38; //Up arrow
    const paddle2Down = 40; //Down arrow. you can figure these out by console-logging the event.keyCode

    switch(keyPressed){
        case(paddle1Up):
            if(paddle1.y>0){ //if statements to prevent the paddles from leaving the game board.
            paddle1.y-=paddleSpeed;
            }
            break;
        case(paddle1Down):
            if(paddle1.y<gameHeight-paddle1.height){ //since paddle1.y is the top left corner - need to account for paddle height in restriction
            paddle1.y+=paddleSpeed;
            }
            break;
        case(paddle2Up):
            if(paddle2.y>0){
            paddle2.y-=paddleSpeed;
            }
            break;
        case(paddle2Down):
            if(paddle2.y<gameHeight-paddle2.height){
            paddle2.y+=paddleSpeed;
            }
            break;
    }
};
function updateScore(){
    scoreText.textContent=`${player1Score} : ${player2Score}`;
};
function resetGame(){
    player1Score=0;
    player2Score=0;
    //reset paddles
    paddle1 = { 
    width: 25, 
    height: 100,
    x: 0,
    y: 0
    }
    paddle2 = {
    width: 25, 
    height: 100,
    x: gameWidth-25,
    y: gameHeight-100
    };
    //reset ball
    ballSpeed=1;
    ballX = 0;
    ballY = 0;
    ballXDirection=0;
    ballYDirection=0;
    updateScore();
    clearInterval(intervalID);
    gameStart();
};