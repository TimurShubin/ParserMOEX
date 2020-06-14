let firstNum = +document.getElementById("firstNum").value;
let secondNum = +document.getElementById("secondNum").value;
let thirdNum = +document.getElementById("thirdNum").value;
let fourthNum = +document.getElementById("fourthNum").value;

let resFirst = document.getElementById("resFirst");
let resSecond = document.getElementById("resSecond");
let resThird = document.getElementById("resThird");
let resFourth = document.getElementById("resFourth");

let first = 0;
let second = 0;
let third = 0;
let fourth = 0;

let listR = [first, second, third, fourth];

let firstPos = 0;
let currentGame = 0;
let toGame = "";

function newGame() {
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/newGame",
		success: function(g) {
			currentGame = g.length - 1;
			$(".left-column").empty();
			$(".btn-check").show();
			$("#firstNum").val(0);
			$("#secondNum").val(0);
			$("#thirdNum").val(0);
			$("#fourthNum").val(0);
			$("#results").empty();
			firstNum = 0;
			secondNum = 0;
			thirdNum = 0;
			fourthNum = 0;

			$(".loader").empty();

			let attemptFirstPos = 0;
			let attemptSecondPos = 0;
			let attemptThirdPos = 0;
			let attemptFourthPos = 0;				
			let lastAttempt = 0;
			listR = [g[currentGame].firstNum, g[currentGame].secondNum, g[currentGame].thirdNum, g[currentGame].fourthNum];
			for(let i = 0; i < g.length; i++) {

				if(g[i].countAttempts !== 0) {
					
					let lastAttempt = g[i].countAttempts-1;
					let attemptFirstPos = g[i].attempts[lastAttempt].firstNum;
					let attemptSecondPos = g[i].attempts[lastAttempt].secondNum;
					let attemptThirdPos = g[i].attempts[lastAttempt].thirdNum;
					let attemptFourthPos = g[i].attempts[lastAttempt].fourthNum;
					let gameId = g[i].gameId;
					$(".left-column").append("<div class='box'><div class='col-sm-12 completeGame'><div class='container'><div class='row'><div class='col-sm-8'><div class='resultBox'><div class='container'><div class='row'><div class='col-sm-3'><h4>" + attemptFirstPos + "</h4><h4>Б</h4></div><div class='col-sm-3'><h4>" + attemptSecondPos + "</h4><h4>Б</h4></div><div class='col-sm-3'><h4>" + attemptThirdPos + "</h4><h4>Б</h4></div><div class='col-sm-3'><h4>" + attemptFourthPos + "</h4><h4>Б</h4></div></div></div></div></div><div class='col-sm-4 featureBox'><div class='col-sm-12'><span class='gameDate'>12.03.2020</span></div><div class='col-sm-12 attempt'><h4>" + lastAttempt + "</h4><span>попыток</span></div></div></div></div></div><div class='col-sm-12 linkToGame' onclick='changeGame(" + gameId + ")'><span>открыть игру (" + gameId + ")</span></div></div>");

				}

			}
			$(".newGame").show();
		},
		fail: function(e) {
			console.log("error: " + e);
		}
	});
}

function selectInput(num, action) {
	switch(num) {
		case 1:
			firstNum = setAction(firstNum, action);
			document.getElementById("firstNum").value = firstNum;
			break;
		case 2:
			secondNum = setAction(secondNum, action);
			document.getElementById("secondNum").value = secondNum;
			break;
		case 3:
			thirdNum = setAction(thirdNum, action);
			document.getElementById("thirdNum").value = thirdNum;
			break;
		case 4:
			fourthNum = setAction(fourthNum, action);
			document.getElementById("fourthNum").value = fourthNum;
			break;
		default:
			console.log('Ошибка. Неверное значение');
			break;
	}
}

function setAction(num, action) {
	switch(action) {
		case 0:
			num+=1;
			if(num > 9) {
				num = 0;
			}
			return num;
		case 1:
			num-=1;
			if(num < 0) {
				num = 9;
			}
			return num;
	}
}

function fillResults() {
		$('#results').empty();
		for(let i = 0; i < listR.length; i++) {
			$("#results").append("<div class='col-sm-2 results'>" + listR[i] + "</div>");
		}
}

function check() {
	let dataSet = {
		"firstNum": firstNum,
		"secondNum": secondNum,
		"thirdNum": thirdNum,
		"fourthNum": fourthNum,
		"resultId": currentGame
	};
		$.ajax({
			type: "POST",
			url: "/checkResults",
			data: JSON.stringify(dataSet),
			contentType: 'application/json',
			success: function(r) {
				listR = [r[0], r[1], r[2], r[3]];
				if(r[0] == "Б" && r[1] == "Б" && r[2] == "Б" && r[3] == "Б") {
					$(".btn-check").hide();
				}
				fillResults();
				newAttempt();
			},
			fail: function() {
				console.log("fail");
			}
		});
}

function loadResults() {
	let endPos = firstPos + 5;
	let searchRange = {
		"start": firstPos,
		"end": endPos
	};
	$.ajax({
		type: "POST",
		url: "/loadResults",
		data: JSON.stringify(searchRange),
		contentType: "application/json",
		success: function(n) {
			firstPos = n.length;
			for(let i = 0; i < n.length; i++) {
				$(".loader").append("<div class='col-sm-12'><div class='container attemptBox'><div class='row'><div class='col-sm-2'><h4></h4></div><div class='col-sm-2'><h4>" + n[i].firstNum + "</h4></div><div class='col-sm-2'><h4>" + n[i].secondNum + "</h4></div><div class='col-sm-2'><h4>" + n[i].thirdNum + "</h4></div><div class='col-sm-2'><h4>" + n[i].fourthNum + "</h4></div><div class='col-sm-2'><h4></h4></div></div></div></div>");
			}
		},
		fail: function(e) {
			console.log("Error: " + e);
		}
	});
}
function showResults(k) {
	for(let i = 0; i < k.length; i++) {
		$(".loader").append("<div class='col-sm-12'><div class='container attemptBox'><div class='row'><div class='col-sm-2'><h4></h4></div><div class='col-sm-2'><h4>" + k[i].firstNum + "</h4></div><div class='col-sm-2'><h4>" + k[i].secondNum + "</h4></div><div class='col-sm-2'><h4>" + k[i].thirdNum + "</h4></div><div class='col-sm-2'><h4>" + k[i].fourthNum + "</h4></div><div class='col-sm-2'><h4></h4></div></div></div></div>");
	}
}
function newAttempt() {
	$(".loader").append("<div class='col-sm-12'><div class='container attemptBox'><div class='row'><div class='col-sm-2'><h4></h4></div><div class='col-sm-2'><h4>" + firstNum + "</h4></div><div class='col-sm-2'><h4>" + secondNum + "</h4></div><div class='col-sm-2'><h4>" + thirdNum + "</h4></div><div class='col-sm-2'><h4>" + fourthNum + "</h4></div><div class='col-sm-2'><h4></h4></div></div></div></div>");
}
function changeGame(toGame) {
	let checkedGame = {
		"gameId": toGame
	}
	$.ajax({
		type: "POST",
		url: "/changeGame",
		data: JSON.stringify(checkedGame),
		contentType: "application/json",
		success: function(c) {
			$(".btn-check").hide();
			$("#firstNum").val(c.firstNum);
			$("#secondNum").val(c.secondNum);
			$("#thirdNum").val(c.thirdNum);
			$("#fourthNum").val(c.fourthNum);
			$("#results").empty();
			$(".loader").empty();
			firstNum = c.firstNum;
			secondNum = c.secondNum;
			thirdNum = c.thirdNum;
			fourthNum = c.fourthNum;
			listR = [c.firstNum, c.secondNum, c.thirdNum, c.fourthNum];

			for(let i = 0; i < c.attempts.length; i++) {
				$(".loader").append("<div class='col-sm-12'><div class='container attemptBox'><div class='row'><div class='col-sm-2'><h4></h4></div><div class='col-sm-2'><h4>" + c.attempts[i].firstNum + "</h4></div><div class='col-sm-2'><h4>" + c.attempts[i].secondNum + "</h4></div><div class='col-sm-2'><h4>" + c.attempts[i].thirdNum + "</h4></div><div class='col-sm-2'><h4>" + c.attempts[i].fourthNum + "</h4></div><div class='col-sm-2'><h4></h4></div></div></div></div>");
			}
		},
		fail: function(e) {
			console.log("Error: " + e);
		}
	});
}