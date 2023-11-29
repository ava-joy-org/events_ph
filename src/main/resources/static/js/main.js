function toggleSearchButton() {
    var searchInput = document.getElementById('searchTxt');
    var searchButton = document.getElementById('searchBtn');

    // Enable the search button if the input is not empty, otherwise, disable it
    searchButton.disabled = searchInput.value.trim() === '';
}