export function SearchModule(searchInputId, searchButtonId, tableBodyId) {
        document.addEventListener('DOMContentLoaded', function () {
            const searchInput = document.getElementById(searchInputId);
            const tableBody = document.getElementById(tableBodyId).getElementsByTagName('tr');

            document.getElementById(searchButtonId).addEventListener('click', function () {
                const searchTerm = searchInput.value.toLowerCase();

                for (let i = 0; i < tableBody.length; i++) {
                    const name = tableBody[i].getElementsByTagName('td')[1].textContent.toLowerCase();

                    if (name.includes(searchTerm)) {
                        tableBody[i].style.display = '';
                    } else {
                        tableBody[i].style.display = 'none';
                    }
                }
            });
        });
}

