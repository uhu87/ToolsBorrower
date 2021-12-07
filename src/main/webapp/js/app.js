const parents = document.querySelectorAll('.parent');

parents.forEach(parent => {

    parent.addEventListener('mouseover', function(event) {
        event.currentTarget.querySelector('.children').style.display = 'block';
        // event.currentTarget.firstElementChild.style.display = 'block';
    });

    parent.addEventListener('mouseout', function(event) {
        event.currentTarget.querySelector('.children').style.display = 'none';
    });

})