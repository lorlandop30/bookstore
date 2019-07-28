

var stars = document.querySelectorAll(".star");
var starValue = document.querySelector("#score");
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

        starValue.value = rating;

    });

});

function resetStars (){
    document.querySelectorAll(".star").forEach(function (e) {
        e.classList.remove("fas");
        e.classList.add("far");
    });
}

// Get the modal
var modal = document.getElementById("myModal");

// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("myImg");
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");
img.onclick = function(){
    modal.style.display = "block";
    modalImg.src = this.src;
    captionText.innerHTML = this.alt;
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

