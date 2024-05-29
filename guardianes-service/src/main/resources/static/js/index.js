function toggleTutorial() {
    var tutorial = document.getElementById("tutorial");
    if (tutorial.style.display === "none" || tutorial.style.display === "") {
        tutorial.style.display = "block";
    } else {
        tutorial.style.display = "none";
    }
}