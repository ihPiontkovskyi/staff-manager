function openForm(id) {
    document.getElementById("deny-form").setAttribute("action", document.URL + "/deny/" + id);
    document.getElementById("deny-popup").style.display = "block";
}

function closeForm() {
    document.getElementById("deny-popup").style.display = "none";
}