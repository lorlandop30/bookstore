

var stars = document.querySelectorAll(".star");
var rating = 0;
var isClicked;

//Adding event listeners to the stars

stars.forEach(function(e) {

    e.addEventListener("mouseover", function(){
        resetStars();
        isClicked = false;
        e.classList.remove("far");
        e.classList.add("fas");
        $("i.fas").prevAll().removeClass("far");
        $("i.fas").prevAll().addClass("fas");


    });

    e.addEventListener("mouseout", function(){
        if(isClicked == false ){ //if no rating has been selected yet
            e.classList.remove("fas");
            e.classList.add("far");
            $("i.far").prevAll().removeClass("fas");
            $("i.far").prevAll().addClass("far");
        }
    });



    e.addEventListener("click", function() {
        isClicked = true;
        rating = $(e).index() + 1; //stores the number of selected stars
        e.classList.remove("far");
        e.classList.add("fas");
        $("i.fas").prevAll().removeClass("far");
        $("i.fas").prevAll().addClass("fas");

    });

});

function resetStars (){
    document.querySelectorAll(".star").forEach(function (e) {
        e.classList.remove("fas");
        e.classList.add("far");
    });
}