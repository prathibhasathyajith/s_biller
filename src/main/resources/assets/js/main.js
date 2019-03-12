function saveActive(ID){
    localStorage.setItem('clicked_ID',ID);
}
function setActive(){
    var id = "#side_"+localStorage.getItem('clicked_ID');
    $(id).addClass("active");
}
$(document).ready(function () {
    setActive();
})
